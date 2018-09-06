package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 行情深度
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Getter
@Builder
public class OrderBook {
    private List<Depth> asks;
    private List<Depth> bids;
}
