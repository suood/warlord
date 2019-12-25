package com.suood.warlord.demo.bean;

import com.suood.warlord.demo.condition.SuoodCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component      
public class Person {
  @Bean(name = "lovePerson")
//  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Lazy(true)
  @Conditional(value = SuoodCondition.class)
  public Person getPerson(){
   return new Person();
  }
}
