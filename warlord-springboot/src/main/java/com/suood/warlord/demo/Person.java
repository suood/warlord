package com.suood.warlord.demo;

import com.google.errorprone.annotations.concurrent.LazyInit;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component      
public class Person {
  @Bean(name = "lovePerson")
//  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Lazy(true)
  public Person getPerson(){
   return new Person();
  }
}
