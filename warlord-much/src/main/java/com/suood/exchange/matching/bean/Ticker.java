package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Getter;

/**
 * 撮合最新ticker
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Getter
@Builder
public class Ticker {

    private double askPrice;

    private double bidPrice;

    private double lastPrice;

    private long lastTime;
}
