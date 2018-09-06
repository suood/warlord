package com.suood.exchange.matching.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 撮合结果
 *
 * @author Alexander
 * @date 2018-02-02
 */
@Data
@Builder
public class MatchingResult {

    private List<MatchingFill> fillList;

    private List<MatchingCancel> cancelList;

    private double volume;

    private double selfVolume;
}
