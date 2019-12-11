package com.suood.warlord.aop;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.RateLimiter;
import com.suood.warlord.annotation.Limiter;
import com.suood.warlord.exception.TooManyRequestException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
@Order(1023)
public class LimiterAop {

  public static Set<String> set = Sets.newConcurrentHashSet();
  public static volatile Map<String, RateLimiter> map = Maps.newConcurrentMap();

  @Pointcut("execution(* *.*(..)) && @annotation(com.suood.warlord.annotation.Limiter)")
  public void apiLimiter() {
  }

  @Around(value = "apiLimiter()")
  public void acquire(JoinPoint joinPoint) throws TooManyRequestException {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
    Limiter limiter = method.getAnnotation(Limiter.class);
    if (null != limiter) {
      String url = requestMapping.path().length == 0 ? requestMapping.value()[0] : requestMapping.path()[0];
      if (set.add(url)) {
        //create RateLimiter and put into map
        RateLimiter rateLimiter = RateLimiter.create(1);
        map.put(url, rateLimiter);
      } else {
        RateLimiter rateLimiter = map.get(url);
        if (!rateLimiter.tryAcquire()) {
          if (rateLimiter.acquire() > limiter.timeOut()) {
            throw new TooManyRequestException(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
          }
        }
      }
    }
  }

}