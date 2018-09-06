package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Getter;

/**
 * 撮合撤单
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Getter
@Builder
public class MatchingCancel {
    private long userId;
    private long orderId;
    private int reason;
}
