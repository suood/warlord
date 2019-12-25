package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackageClasses = {com.suood.warlord.demo.bean.ProcessGirls.class})
@ComponentScan(basePackages = {"com.suood.warlord.demo.bean"})
public class FactoryBeanConfig {

}
