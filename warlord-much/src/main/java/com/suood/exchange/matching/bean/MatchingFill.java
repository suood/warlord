package com.suood.exchange.matching.bean;

import com.suood.exchange.matching.enums.Liquidity;
import com.suood.exchange.matching.enums.OrderSide;
import lombok.Builder;
import lombok.Getter;

/**
 * 撮合成交记录
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Getter
@Builder
public class MatchingFill {
    private long userId;
    private long orderId;
    private long targetOrderId;
    private OrderSide side;
    private double price;
    private double size;
    /**
     * 1:Maker, 2:Taker
     */
    private Liquidity liquidity;
    private long createdDate;
    /**
     * 是否是跟自己成交
     */
    private boolean self;

}
