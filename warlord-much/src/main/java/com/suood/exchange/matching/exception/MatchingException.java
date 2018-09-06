package com.suood.exchange.matching.exception;

/**
 * 撮合异常
 *
 * @author Alexander
 * @date 2018-07-25
 */
public class MatchingException extends RuntimeException {

    public MatchingException(String message) {
        super(message);
    }
}