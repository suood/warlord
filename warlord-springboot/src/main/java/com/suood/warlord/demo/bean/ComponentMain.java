package com.suood.warlord.demo.bean;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentMain {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
    context.addApplicationListener(new ApplicationListener(){

      @Override
      public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("a event ");
        System.out.println(event.getSource().getClass());
      }
    });
    System.out.println("test lazy property");
    Object o = context.getBean("lovePerson");
    System.out.println(o);

    context.close();
  }
}
