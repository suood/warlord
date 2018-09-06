package com.suood.exchange.matching.bean;

import com.suood.exchange.matching.enums.OrderSide;
import lombok.Builder;
import lombok.Data;

/**
 * 撮合买卖价格深度
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Data
@Builder
public class PriceLevel {
    private MatchingOrder mFirstVisible, mLastVisible;
    private MatchingOrder mFirstHidden, mLastHidden;
    private OrderSide side;
    private double price;
    private double mVisibleSize, mHiddenSize;
    private int mVisibleCount, mHiddenCount;

    public void addOrder(MatchingOrder order) {
        if (order.isHidden()) {
            mHiddenSize += order.getSize() - order.getExecutedSize();
            ++mHiddenCount;
            if (mFirstHidden == null) {
                mFirstHidden = mLastHidden = order;
            } else {
                order.setPrev(mLastHidden);
                mLastHidden.setNext(order);
                mLastHidden = order;
            }
        } else {
            mVisibleSize += order.getSize() - order.getExecutedSize();
            ++mVisibleCount;
            if (mFirstVisible == null) {
                mFirstVisible = mLastVisible = order;
            } else {
                order.setPrev(mLastVisible);
                mLastVisible.setNext(order);
                mLastVisible = order;
            }
        }
    }

    /**
     * removeOrder
     *
     * @param order
     * @return 返回指定订单的下一个订单
     */
    public MatchingOrder removeOrder(MatchingOrder order) {
        if (order.isHidden()) {
            mHiddenSize -= order.getSize() - order.getExecutedSize();
            --mHiddenCount;
            if (mFirstHidden.getId() == order.getId()) {
                mFirstHidden = order.getNext();
            }
            if (mLastHidden.getId() == order.getId()) {
                mLastHidden = order.getPrev();
            }
        } else {
            mVisibleSize -= order.getSize() - order.getExecutedSize();
            --mVisibleCount;
            if (mFirstVisible.getId() == order.getId()) {
                mFirstVisible = order.getNext();
            }
            if (mLastVisible.getId() == order.getId()) {
                mLastVisible = order.getPrev();
            }
        }
        return order.remove();
    }

    /**
     * executeOrder
     *
     * @param order  待执行成交的单
     * @param volume 成交量
     * @return 返回成交过的单
     */
    public MatchingOrder executeOrder(MatchingOrder order, double volume) {
        order.setExecutedSize(order.getExecutedSize() + volume);
        if (order.isHidden()) {
            mHiddenSize -= volume;
        } else {
            mVisibleSize -= volume;
        }
        return order;
    }

    /**
     * 修改单量
     *
     * @param order 待修改的单
     * @param size  新下单量
     */
    public void changeSize(MatchingOrder order, double size) {
        if (order.isHidden()) {
            mHiddenSize += size - order.getSize();
        } else {
            mVisibleSize += size - order.getSize();
        }
        order.setSize(size);
    }
}