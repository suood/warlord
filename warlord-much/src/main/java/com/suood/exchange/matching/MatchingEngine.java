package com.suood.exchange.matching;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.suood.exchange.matching.bean.*;
import com.suood.exchange.matching.enums.Liquidity;
import com.suood.exchange.matching.enums.OrderSide;
import com.suood.exchange.matching.enums.OrderType;
import com.suood.exchange.matching.exception.MatchingException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 撮合引擎
 *
 * @author Alexander
 * @date 2018-07-25
 */
public class MatchingEngine {

    private double LAST_PRICE = 0;

    private long LAST_TRADE_TIME = 0;

    private int MAX_DEPTH = 1000;

    private int MAX_TRADE_SIZE = 1000;

    /**
     * 成交历史，按时间顺序排列
     */
    private List<Trade> TRADE_HISTORY = Lists.newLinkedList();

    /**
     * 卖单队列  从小到大排列，最小的为卖一(队头)
     */
    private TreeMap<Double, PriceLevel> ASK_QUEUE = Maps.newTreeMap();

    /**
     * 买单队列  从大到小排列，最大的为买(队头)
     */
    private TreeMap<Double, PriceLevel> BID_QUEUE = Maps.newTreeMap(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    });

    private MatchingEngine() {
    }

    /**
     * 创建撮合引擎实例
     *
     * @param maxDepth  获取orderBook最大深度
     * @param orderList 初始化订单
     * @param orderList 成交历史
     * @return
     */
    public static MatchingEngine createMatchingEngine(int maxDepth, int maxTradeSize, List<MatchingOrder> orderList,
                                                      List<Trade> tradeList) {
        if (maxDepth < 1 || maxTradeSize < 1) {
            throw new MatchingException("init param error");
        }
        MatchingEngine engine = new MatchingEngine();
        engine.MAX_DEPTH = maxDepth;
        engine.MAX_TRADE_SIZE = maxTradeSize;
        if (!CollectionUtils.isEmpty(orderList)) {
            for (MatchingOrder order : orderList) {
                engine.putMatchingQueue(order);
            }
        }
        if (!CollectionUtils.isEmpty(tradeList)) {
            Trade trade = tradeList.get(tradeList.size() - 1);
            engine.LAST_PRICE = trade.getPrice();
            engine.LAST_TRADE_TIME = trade.getTime();
            engine.TRADE_HISTORY.addAll(tradeList);
        }
        return engine;
    }

    /**
     * 清空撮合
     */
    public synchronized void clear() {
        LAST_PRICE = 0;
        MAX_DEPTH = 1000;
        MAX_TRADE_SIZE = 1000;
        LAST_TRADE_TIME = 0;
        ASK_QUEUE.clear();
        BID_QUEUE.clear();
        TRADE_HISTORY.clear();
    }

    /**
     * 执行下单撮合
     *
     * @param order 待撮合订单
     * @return
     */
    public synchronized MatchingResult executeMatching(MatchingOrder order) {
        if (order.getSide() != OrderSide.BUY && order.getSide() != OrderSide.SELL) {
            throw new MatchingException("order side error");
        }

        List<MatchingFill> fillList = Lists.newArrayList();
        List<MatchingCancel> cancelList = Lists.newArrayList();
        MatchingResult result = MatchingResult.builder()
            .fillList(fillList)
            .cancelList(cancelList)
            .build();
        MatchContext matchContext = executeMatchingVirtual(order);
        if (matchContext == null) {
            if (order.isMarket()) {
                return result;
            }
            if (order.getTimeInForce() == OrderType.GTC) {
                putMatchingQueue(order);
            } else {
                cancelList.add(MatchingCancel.builder()
                    .orderId(order.getId())
                    .userId(order.getUserId())
                    .build());
            }
            return result;
        }
        // 成交量
        result.setVolume(matchContext.volume);
        if (matchContext.volume < order.getSize() - order.getExecutedSize()) {
            switch (order.getTimeInForce()) {
                // IOC: 撤销剩余订单
                case IOC:
                    cancelList.add(MatchingCancel.builder()
                        .orderId(order.getId())
                        .userId(order.getUserId())
                        .build());
                    break;
                // FOK: 撤销整个订单
                case FOK:
                    cancelList.add(MatchingCancel.builder()
                        .orderId(order.getId())
                        .userId(order.getUserId())
                        .build());
                    return result;
                // GTC: 放入 OrderBook
                default:
                    order.setExecutedSize(order.getExecutedSize() + matchContext.volume);
                    putMatchingQueue(order);
                    break;
            }
        }
        matchContext.order = order;
        executeMatching(matchContext, result);
        return result;
    }

    /**
     * 测试成交，下市价单看成交情况
     *
     * @param side   买单还是卖单
     * @param userId 下单用户id
     * @param size   下单量
     * @return 成交结果
     */
    public synchronized TestMatchingResult testMatching(OrderSide side, long userId, double size) {
        // 按照市价单测试下单
        MatchingOrder order = MatchingOrder.builder()
            .userId(userId)
            .side(side)
            .size(size)
            .market(true)
            .build();
        MatchContext matchContext = executeMatchingVirtual(order);
        TestMatchingResult result = new TestMatchingResult();
        result.matchContext = matchContext;
        result.fillPriceList = Lists.newArrayList();
        if (matchContext != null && matchContext.volume > 0) {
            for (FilledPriceLevel filledPriceLevel : matchContext.priceLevelList) {
                if (!CollectionUtils.isEmpty(filledPriceLevel.visibleList)) {
                    for (FilledOrder visible : filledPriceLevel.visibleList) {
                        result.fillPriceList.add(MatchingFillPrice.builder()
                            .price(filledPriceLevel.priceLevel.getPrice())
                            .size(visible.volume)
                            .selfSize(userId == visible.matchingOrder.getUserId() ? visible.volume : 0)
                            .build());
                    }
                }
                if (!CollectionUtils.isEmpty(filledPriceLevel.hiddenList)) {
                    for (FilledOrder hidden : filledPriceLevel.hiddenList) {
                        result.fillPriceList.add(MatchingFillPrice.builder()
                            .price(filledPriceLevel.priceLevel.getPrice())
                            .size(hidden.volume)
                            .selfSize(userId == hidden.matchingOrder.getUserId() ? hidden.volume : 0)
                            .build());
                    }
                }
            }
        }
        return result;
    }

    /**
     * 执行测试下单撮合结果成交
     *
     * @param testMatchingResult 测试结果
     * @param order              市价单
     * @return
     */
    public synchronized MatchingResult executeTestMatching(TestMatchingResult testMatchingResult, MatchingOrder order) {
        List<MatchingFill> fillList = Lists.newArrayList();
        List<MatchingCancel> cancelList = Lists.newArrayList();
        MatchingResult result = MatchingResult.builder()
            .fillList(fillList)
            .cancelList(cancelList)
            .build();
        testMatchingResult.matchContext.order = order;
        // 执行测试下单撮合结果成交
        executeMatching(testMatchingResult.matchContext, result);
        return result;
    }

    /**
     * 将订单放入队列
     *
     * @param order
     */
    public synchronized void putMatchingQueue(MatchingOrder order) {
        TreeMap<Double, PriceLevel> queue;
        if (order.getSide() == OrderSide.BUY) {
            queue = BID_QUEUE;
        } else if (order.getSide() == OrderSide.SELL) {
            queue = ASK_QUEUE;
        } else {
            throw new MatchingException("order side error");
        }
        PriceLevel priceLevel = queue.get(order.getPrice());
        if (priceLevel == null) {
            priceLevel = PriceLevel.builder()
                .side(order.getSide())
                .price(order.getPrice())
                .build();
            queue.put(order.getPrice(), priceLevel);
        }
        priceLevel.addOrder(order);
    }

    /**
     * 获取行情
     *
     * @return
     */
    public Ticker getTicker() {
        return Ticker.builder()
            .askPrice(isEmpty(ASK_QUEUE) ? 0 : ASK_QUEUE.firstKey())
            .bidPrice(isEmpty(BID_QUEUE) ? 0 : BID_QUEUE.firstKey())
            .lastPrice(LAST_PRICE)
            .lastTime(LAST_TRADE_TIME)
            .build();
    }

    /**
     * 获取orderBook
     *
     * @return
     */
    public synchronized OrderBook getOrderBook() {
        return OrderBook.builder()
            .asks(this.getMatchingOrderBook(MAX_DEPTH, OrderSide.SELL))
            .bids(this.getMatchingOrderBook(MAX_DEPTH, OrderSide.BUY))
            .build();
    }

    /**
     * 获取成交历史
     *
     * @return
     */
    public synchronized List<Trade> getTradeHistory() {
        return Lists.newArrayList(TRADE_HISTORY);
    }

    /**
     * 不经过撮合，直接插成交记录
     *
     * @param side  下单方向
     * @param price 成交价格
     * @param size  成交数量
     * @return
     */
    public synchronized MatchingResult fillNoMatching(OrderSide side, double price, double size) {
        if (side != OrderSide.BUY && side != OrderSide.SELL) {
            throw new MatchingException("order side error");
        }
        return MatchingResult.builder()
            .fillList(makeFill(0, 0, 0, 0, side, price, size))
            .build();
    }

    /**
     * 批量撤单，撤掉撮合队列中订单
     *
     * @param orderList 需要撤掉的订单集合
     * @return 撤单结果
     */
    public synchronized MatchingResult cancelOrder(List<MatchingOrder> orderList) {
        List<MatchingCancel> cancelList = Lists.newArrayList();
        for (MatchingOrder order : orderList) {
            if (this.removeMatchingOrder(order)) {
                MatchingCancel cancel = MatchingCancel.builder()
                    .orderId(order.getId())
                    .userId(order.getUserId())
                    .build();
                cancelList.add(cancel);
            }
        }
        return MatchingResult.builder()
            .cancelList(cancelList)
            .build();
    }

    /**
     * 移除撮合队列中订单
     *
     * @param order 需要移除的订单
     * @return 移除结果
     */
    private boolean removeMatchingOrder(MatchingOrder order) {
        if (order.getId() <= 0) {
            throw new MatchingException("order id error");
        }
        TreeMap<Double, PriceLevel> queue;
        if (order.getSide() == OrderSide.BUY) {
            queue = BID_QUEUE;
        } else if (order.getSide() == OrderSide.SELL) {
            queue = ASK_QUEUE;
        } else {
            throw new MatchingException("order side error");
        }

        // 未找到该价格下单列表
        PriceLevel priceLevel = queue.get(order.getPrice());
        if (priceLevel == null) {
            return false;
        }
        MatchingOrder matchingOrder = order.isHidden() ? priceLevel.getMFirstHidden() : priceLevel.getMFirstVisible();
        while (matchingOrder != null) {
            if (matchingOrder.getId() == order.getId()) {
                break;
            }
            matchingOrder = matchingOrder.getNext();
        }
        // 未找到改id对应单
        if (matchingOrder == null) {
            return false;
        }

        // 删除撮合队列中的order
        priceLevel.removeOrder(matchingOrder);
        // 撤销这个单子之后如果集合里没有订单，这个档位直接删掉
        if (priceLevel.getMVisibleCount() == 0 && priceLevel.getMHiddenCount() == 0) {
            queue.remove(priceLevel.getPrice());
        }
        return true;
    }

    /**
     * 根据matchContext生成撮合成交结果
     *
     * @param matchContext 撮合结果上下文
     * @param result       待返回的撮合成交结果
     */
    private void executeMatching(MatchContext matchContext, MatchingResult result) {
        MatchingOrder order = matchContext.order;
        TreeMap<Double, PriceLevel> oppositeQueue = order.getSide() == OrderSide.BUY ? ASK_QUEUE : BID_QUEUE;
        List<MatchingFill> fillList = result.getFillList();
        for (FilledPriceLevel filledPriceLevel : matchContext.priceLevelList) {
            List<MatchingFill> visibleList = executeMatching(filledPriceLevel.priceLevel,
                filledPriceLevel.visibleList, order);
            List<MatchingFill> hiddenList = executeMatching(filledPriceLevel.priceLevel,
                filledPriceLevel.hiddenList, order);
            if (!CollectionUtils.isEmpty(visibleList)) {
                fillList.addAll(visibleList);
            }
            if (!CollectionUtils.isEmpty(hiddenList)) {
                fillList.addAll(hiddenList);
            }
            if (filledPriceLevel.priceLevel.getMVisibleCount() == 0
                && filledPriceLevel.priceLevel.getMHiddenCount() == 0) {
                oppositeQueue.remove(filledPriceLevel.priceLevel.getPrice());
            }
            if (filledPriceLevel.selfSize > 0) {
                result.setSelfVolume(result.getSelfVolume() + filledPriceLevel.selfSize);
            }
        }
    }

    /**
     * 根据撮合结果生成fillOrder，并映射到撮合队列中priceLevel
     *
     * @param priceLevel      撮合队列中priceLevel
     * @param filledOrderList 撮合结果
     * @param order           新的下单
     * @return 成交结果
     */
    private List<MatchingFill> executeMatching(PriceLevel priceLevel, List<FilledOrder> filledOrderList,
                                               MatchingOrder order) {
        List<MatchingFill> fillList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(filledOrderList)) {
            for (FilledOrder filledOrder : filledOrderList) {
                MatchingOrder matchingOrder = filledOrder.matchingOrder;

                fillList.addAll(makeFill(order.getUserId(), matchingOrder.getUserId(), order.getId(),
                    matchingOrder.getId(), order.getSide(), matchingOrder.getPrice(), filledOrder.volume));
                if (filledOrder.volume < matchingOrder.getSize() - matchingOrder.getExecutedSize()) {
                    priceLevel.executeOrder(matchingOrder, filledOrder.volume);
                } else {
                    priceLevel.removeOrder(matchingOrder);
                }
            }
        }
        return fillList;
    }

    /**
     * 创建撮合成交记录
     *
     * @param takerId      下单吃单用户id
     * @param makerId      挂单被吃用户id
     * @param takerOrderId 下单id
     * @param makerOrderId 挂单id
     * @param side         下单方向
     * @param price        成交价格
     * @param size         成交数量
     * @return
     */
    private List<MatchingFill> makeFill(long takerId, long makerId, long takerOrderId, long makerOrderId,
                                        OrderSide side, double price, double size) {
        List<MatchingFill> fillList = Lists.newArrayList();
        long now = System.currentTimeMillis();
        MatchingFill takerFill = MatchingFill.builder()
            .userId(takerId)
            .orderId(takerOrderId)
            .targetOrderId(makerOrderId)
            .side(side)
            .price(price)
            .size(size)
            .liquidity(Liquidity.TAKER)
            .self(takerId == makerId)
            .createdDate(now)
            .build();

        MatchingFill makerFill = MatchingFill.builder()
            .userId(makerId)
            .orderId(makerOrderId)
            .targetOrderId(takerOrderId)
            .side(side .equals(OrderSide.BUY)  ? OrderSide.SELL : OrderSide.BUY)
            .price(price)
            .size(size)
            .liquidity(Liquidity.MAKER)
            .self(takerId == makerId)
            .createdDate(now)
            .build();

        fillList.add(takerFill);
        fillList.add(makerFill);
        LAST_PRICE = price;
        LAST_TRADE_TIME = now;
        TRADE_HISTORY.add(Trade.builder()
            .price(price)
            .size(size)
            .time(now)
            .build());
        if (TRADE_HISTORY.size() > MAX_TRADE_SIZE) {
            TRADE_HISTORY.remove(0);
        }
        return fillList;
    }

    /**
     * 模拟执行下单撮合，撮合结果会被返回，等待真正执行撮合时映射进撮合队列
     *
     * @param order 新的下单
     * @return 撮合结果上下文
     */
    private MatchContext executeMatchingVirtual(MatchingOrder order) {
        TreeMap<Double, PriceLevel> oppositeQueue;
        OrderSide side = order.getSide();
        if (side == OrderSide.BUY) {
            oppositeQueue = ASK_QUEUE;
        } else if (side == OrderSide.SELL) {
            oppositeQueue = BID_QUEUE;
        } else {
            throw new MatchingException("order side error");
        }

        List<FilledPriceLevel> priceLevelList = Lists.newArrayList();
        double size = order.getSize() - order.getExecutedSize();
        double price = order.getPrice();
        if (order.isMarket()) {
            price = side == OrderSide.SELL ? 0 : Double.MAX_VALUE;
        }
        for (Entry<Double, PriceLevel> matchingEntry : oppositeQueue.entrySet()) {
            // 单被吃完或者匹配不到合理价格时结束撮合
            if (matchingEntry == null || matchingEntry.getValue() == null || size <= 0
                || (side == OrderSide.BUY && matchingEntry.getKey() > price)
                || (side == OrderSide.SELL && matchingEntry.getKey() < price)) {
                break;
            }
            size = executeMatchingPrice(priceLevelList, matchingEntry.getValue(), order.getUserId(), size);
        }
        if (CollectionUtils.isEmpty(priceLevelList)) {
            return null;
        }
        MatchContext matchContext = new MatchContext();
        matchContext.priceLevelList = priceLevelList;
        matchContext.volume = order.getSize() - order.getExecutedSize() - size;
        return matchContext;
    }

    /**
     * 执行每个priceLevel上的撮合
     *
     * @param priceLevelList 撮合结果priceLevel集合
     * @param priceLevel     待执行撮合中priceLevel
     * @param userId         下单用户id
     * @param size           待撮合单量
     * @return 撮合后剩余单量
     */
    private double executeMatchingPrice(List<FilledPriceLevel> priceLevelList, PriceLevel priceLevel, long userId,
                                        double size) {
        FilledPriceLevel filledPriceLevel = new FilledPriceLevel();
        filledPriceLevel.priceLevel = priceLevel;
        MatchingOrder visibleMatchingOrder = priceLevel.getMFirstVisible();
        if (visibleMatchingOrder != null && size > 0) {
            filledPriceLevel.visibleList = Lists.newArrayList();
            size = executeMatchingOrder(filledPriceLevel, visibleMatchingOrder, userId, size);
        }
        MatchingOrder hiddenMatchingOrder = priceLevel.getMFirstHidden();
        if (hiddenMatchingOrder != null && size > 0) {
            filledPriceLevel.hiddenList = Lists.newArrayList();
            size = executeMatchingOrder(filledPriceLevel, hiddenMatchingOrder, userId, size);
        }
        // 有匹配的单
        if (!CollectionUtils.isEmpty(filledPriceLevel.visibleList) ||
            !CollectionUtils.isEmpty(filledPriceLevel.hiddenList)) {
            priceLevelList.add(filledPriceLevel);
        }
        return size;
    }

    /**
     * 递归执行每个priceLevel上可见单或隐藏单的撮合
     *
     * @param filledPriceLevel 撮合结果priceLevel
     * @param matchingOrder    待执行撮合中订单
     * @param userId           下单用户id
     * @param size             待撮合单量
     * @return 撮合后剩余单量
     */
    private double executeMatchingOrder(FilledPriceLevel filledPriceLevel, MatchingOrder matchingOrder, long userId,
                                        double size) {
        if (matchingOrder == null || size <= 0) {
            return size;
        }
        FilledOrder filledOrder = new FilledOrder();
        List<FilledOrder> filledList = matchingOrder.isHidden() ? filledPriceLevel.hiddenList
            : filledPriceLevel.visibleList;
        filledList.add(filledOrder);
        filledOrder.matchingOrder = matchingOrder;
        // 实际成交量
        filledOrder.volume = Math.min(matchingOrder.getSize() - matchingOrder.getExecutedSize(), size);
        // 如果和自己成交
        if (matchingOrder.getUserId() == userId) {
            filledPriceLevel.selfSize += filledOrder.volume;
        }
        return executeMatchingOrder(filledPriceLevel, matchingOrder.getNext(), userId, size - filledOrder.volume);
    }

    /**
     * 构建买卖orderBook
     *
     * @param count 条数
     * @param side  买卖方向
     * @return
     */
    private List<Depth> getMatchingOrderBook(int count, OrderSide side) {
        TreeMap<Double, PriceLevel> queue;
        if (side == OrderSide.BUY) {
            queue = BID_QUEUE;
        } else {
            queue = ASK_QUEUE;
        }
        int size = 0;
        List<Depth> depth = Lists.newArrayList();
        if (!isEmpty(queue)) {
            for (Entry<Double, PriceLevel> entry : queue.entrySet()) {
                if (size >= count) {
                    break;
                }
                PriceLevel level = entry.getValue();
                if (level == null || level.getMVisibleSize() <= 0) {
                    continue;
                }
                depth.add(Depth.builder()
                    .price(level.getPrice())
                    .size(level.getMVisibleSize())
                    .build());
                ++size;
            }
        }
        return depth;
    }

    public class TestMatchingResult {
        private MatchContext matchContext;
        private List<MatchingFillPrice> fillPriceList;

        public List<MatchingFillPrice> getFilledResultList() {
            return fillPriceList;
        }
    }

    private class MatchContext {
        private MatchingOrder order;
        private List<FilledPriceLevel> priceLevelList;
        private double volume;
    }

    private class FilledPriceLevel {
        private PriceLevel priceLevel;
        private List<FilledOrder> visibleList;
        private List<FilledOrder> hiddenList;
        private double selfSize;
    }

    private class FilledOrder {
        private MatchingOrder matchingOrder;
        private double volume;
    }

    private boolean isEmpty(Map map){
       return map == null || map.isEmpty();
    }
}
