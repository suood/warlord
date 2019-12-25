package com.suood.warlord.demo.bean;

import com.suood.warlord.demo.condition.ChooseImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Person.class, ChooseImportSelector.class})
public class ImportConfig {

}
