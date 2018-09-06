package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Getter;

/**
 * orderBook买卖深度
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Getter
@Builder
public class Depth {
    private double price;
    private double size;
}
