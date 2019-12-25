package com.suood.warlord.demo.condition;

import com.suood.warlord.demo.bean.HelloKitty;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class HelloKittyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//    注册定义
    //TODO importingClassMetadata 获取注解信息 根据不同的注解做出不同的逻辑
    System.out.println("注册定义");
    RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(HelloKitty.class);
    registry.registerBeanDefinition("helloKitty",rootBeanDefinition);
  }
}
