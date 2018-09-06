package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Getter;

/**
 * 成交记录
 *
 * @author Alexander
 * @date 2018-07-26
 */
@Getter
@Builder
public class Trade {
    private double price;
    private double size;
    private long time;
}