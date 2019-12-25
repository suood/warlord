package com.suood.warlord.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ResolvableType;

public class SelfBeanFactory  implements BeanFactory {


  @Override
  public Object getBean(String name) throws BeansException {
    return null;
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return null;
  }

  @Override
  public Object getBean(String name, Object... args) throws BeansException {
    return null;
  }

  @Override
  public <T> T getBean(Class<T> requiredType) throws BeansException {
    return null;
  }

  @Override
  public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
    return null;
  }

  @Override
  public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
    return null;
  }

  @Override
  public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
    return null;
  }

  @Override
  public boolean containsBean(String name) {
    return false;
  }

  @Override
  public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
    return false;
  }

  @Override
  public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
    return false;
  }

  @Override
  public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
    return false;
  }

  @Override
  public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
    return false;
  }

  @Override
  public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
    return null;
  }

  @Override
  public String[] getAliases(String name) {
    return new String[0];
  }
}
