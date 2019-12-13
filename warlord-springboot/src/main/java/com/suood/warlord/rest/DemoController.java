package com.suood.warlord.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.google.common.collect.Maps;
import com.suood.warlord.annotation.Limiter;
import io.github.bucket4j.AsyncBucket;
import io.github.bucket4j.AsyncScheduledBucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BlockingBucket;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.BucketListener;
import io.github.bucket4j.BucketState;
import io.github.bucket4j.ConfigurationBuilder;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.EstimationProbe;
import io.github.bucket4j.Refill;
import java.time.Duration;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController       
public class DemoController {

  @RequestMapping(path = "/hi/{id}", method = RequestMethod.GET,produces = APPLICATION_JSON_UTF8_VALUE)
  @Limiter(maxPerSecond = 1) // aop 1 request/per second
  public Map guavaRateLimiter(@PathVariable("id") String id){
    System.out.println(System.currentTimeMillis());
    Map map = Maps.newHashMap();
    map.put(id,id);
    return map;
  }

  public Map bucket4jRateLimiter(){
                    //设定带宽位每50毫秒2个token 时间窗口逐渐向前滑动
    long initialNum  = 42l;
    Bucket4j.builder()
    // allows 1000 tokens per 1 minute
       .addLimit(Bandwidth.simple(1000, Duration.ofMinutes(1)).withInitialTokens(initialNum))
        // but not often then 50 tokens per 1 second
        .addLimit(Bandwidth.simple(50, Duration.ofSeconds(1)))
        .build();
    int initialTokens = 42;
    Bandwidth limit = Bandwidth
        .simple(1000, Duration.ofHours(1))
        .withInitialTokens(initialTokens);
//    Bucket bucket =
    Bucket4j.builder()
        .addLimit(limit)
        .build();

//    Bandwidth.classic(10l, Refill)
    return null;
  }
}
