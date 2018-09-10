package com.suood.exchange.matching.enums;

import com.suood.exchange.matching.exception.MatchingException;

/**
 * 订单类型
 *
 * @author Alexander
 * @date 2018-07-24
 */
public enum OrderType {

//    如选择GTC,市价单全部交易量都会以最佳可成交价格执行,可以保证成交,但不能保证价格.
//
//    如选择IOC,市价单会在一个最佳可成交价执行尽量多的交易量,此单据可能被部份执行,剩余的部份将会自动删除。
//
//    如选择FOK， 市价单要么在一个最佳可成交价上全部成交，要么就会直接删除，即不会分多次成交，也不会部份成交。

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