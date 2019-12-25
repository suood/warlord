package com.suood.warlord.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages={"com.suood.warlord.demo"}, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})} )
//    ,@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {XXXX.class}))
public class MainConfig {

}
