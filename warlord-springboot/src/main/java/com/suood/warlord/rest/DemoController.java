package com.suood.warlord.rest;

import com.google.common.collect.Maps;
import com.suood.warlord.annotation.Limiter;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController       
public class DemoController {

  @RequestMapping(path = "/hi/{id}", method = RequestMethod.GET)
  @Limiter(maxPerSecond = 1) // aop 1 request/per second
  public Map getInfo(@PathVariable("id") String id){
    System.out.println(System.currentTimeMillis());
    Map map = Maps.newHashMap();
    map.put(id,id);
    return map;
  }
}
