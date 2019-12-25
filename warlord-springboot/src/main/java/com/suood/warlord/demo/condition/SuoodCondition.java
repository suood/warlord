package com.suood.warlord.demo.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SuoodCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

    System.out.println("TODO something about the job which u need to do with Conditional");
//    context.getEnvironment().getActiveProfiles();   获取当前环境标识
    return context.getBeanFactory().containsBean("person");
    // TODO something about the job which u need to do.
    //return false;
  }
}
