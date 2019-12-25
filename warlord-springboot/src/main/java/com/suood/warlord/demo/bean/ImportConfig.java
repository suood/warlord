package com.suood.warlord.demo.bean;

import com.suood.warlord.demo.condition.ChooseImportSelector;
import com.suood.warlord.demo.condition.HelloKittyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Person.class, ChooseImportSelector.class,HelloKitty.class, HelloKittyImportBeanDefinitionRegistrar.class})
public class ImportConfig {

}
