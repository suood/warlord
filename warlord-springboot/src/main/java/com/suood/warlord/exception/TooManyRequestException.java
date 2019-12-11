package com.suood.warlord.exception;

public class TooManyRequestException extends RuntimeException {

  public TooManyRequestException(String message) {
    super(message);
  }
}
