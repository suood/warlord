package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan(basePackageClasses = {com.suood.warlord.demo.bean.ProcessGirls.class})
@ComponentScan(basePackages = {"com.suood.warlord.demo.bean"})
@PropertySource(value = {"classpath:demo.properties"})
public class FactoryBeanConfig {

}
