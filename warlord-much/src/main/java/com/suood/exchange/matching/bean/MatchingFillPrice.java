package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Getter;

/**
 * 撮合成交价格详情
 *
 * @author Alexander
 * @date 2018-07-25
 */
@Getter
@Builder
public class MatchingFillPrice {
    private double price;
    private double size;
    private double selfSize;
}