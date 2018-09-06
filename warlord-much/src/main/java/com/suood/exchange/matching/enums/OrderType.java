package com.suood.exchange.matching.enums;

import com.suood.exchange.matching.exception.MatchingException;

/**
 * 订单类型
 *
 * @author Alexander
 * @date 2018-07-24
 */
public enum OrderType {

    GTC(0),

    IOC(1),

    FOK(2);

    private int code;

    OrderType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public OrderType valueOf(int code) {
        for (OrderType o : OrderType.values()) {
            if (o.code == code) {
                return o;
            }
        }
        throw new MatchingException("order type not exists");
    }
}