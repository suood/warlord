package com.suood.warlord.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limiter {

  int maxPerSecond() default 1;

  int maxPerMinute() default 100;

  int maxPerHour() default 1000;

  int maxPerDay() default 10000;
  double timeOut() default 1;


}
