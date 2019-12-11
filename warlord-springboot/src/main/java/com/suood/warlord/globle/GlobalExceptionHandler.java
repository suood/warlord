package com.suood.warlord.globle;

import com.suood.warlord.exception.TooManyRequestException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  
  @ExceptionHandler(TooManyRequestException.class)
  @ResponseBody
  public ResponseEntity methodArgumentNotValidExceptionHandler(TooManyRequestException t) {
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).body(HttpStatus.TOO_MANY_REQUESTS.name());
  }
}
