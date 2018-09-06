package com.suood.exchange.matching.enums;


import com.suood.exchange.matching.exception.MatchingException;

/**
 * 成交类别
 *
 * @author Alexander
 * @date 2018-07-24
 */
public enum Liquidity {

    MAKER(1),

    TAKER(2);

    private int code;

    Liquidity(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public Liquidity valueOf(int code) {
        for (Liquidity l : Liquidity.values()) {
            if (l.code == code) {
                return l;
            }
        }
        throw new MatchingException("fill liquidity not exists");
    }
}