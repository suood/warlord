package com.suood.exchange.matching.bean;

import com.suood.exchange.matching.enums.OrderSide;
import com.suood.exchange.matching.enums.OrderType;
import lombok.Builder;
import lombok.Data;

/**
 * 撮合订单
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Data
@Builder
public class MatchingOrder {
    private long id;
    private long userId;
    private double price;
    private double size;
    private OrderSide side;
    private double executedSize;
    /**
     * GTC 0, IOC 1, FOK 2
     */
    private OrderType timeInForce;
    /**
     * 只做maker
     */
    private boolean postOnly;
    /**
     * 是否是隐藏单
     */
    private boolean hidden;
    /**
     * 是否是市价单
     */
    private boolean market;

    private MatchingOrder prev;

    private MatchingOrder next;

    /**
     * 返回下一个单
     *
     * @return
     */
    public MatchingOrder remove() {
        if (this.prev != null) {
            this.prev.next = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
        MatchingOrder res = this.next;
        this.prev = null;
        this.next = null;
        return res;
    }
}
