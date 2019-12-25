package com.suood.warlord.demo.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ChooseImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    //TODO 可以获取导入类的注解信息
    // TODO 如果找到了指定的 注解 处理业务逻辑
    return new String[0];
  }
}
