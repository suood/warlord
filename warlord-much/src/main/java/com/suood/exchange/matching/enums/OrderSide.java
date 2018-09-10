package com.suood.exchange.matching.enums;

import com.suood.exchange.matching.exception.MatchingException;
import lombok.Getter;

/**
 * 订单方向（买、卖）
 *
 * @author Alexander
 * @date 2017/9/2
 */
@Getter
public enum OrderSide {

    BUY(1),

    SELL(2);

    private int code;

     OrderSide(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public OrderSide valueOf(int code) {
        for (OrderSide o : OrderSide.values()) {
            if (o.code == code) {
                return o;
            }
        }
        throw new MatchingException("order side not exists");
    }
}
