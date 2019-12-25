package com.suood.warlord.demo.bean;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class SuoodBeanScanFilter implements TypeFilter {

  @Override
  public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
      throws IOException {

    /* 获取当前类的注解源信息 */
    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
//获取当前类的class的源信息
    ClassMetadata classMetadata = metadataReader.getClassMetadata();
//获取当前类的资源信息
    Resource resource = metadataReader.getResource();
    if (classMetadata.getClassName().contains("dao")) {
      //TODO 这里可以干点儿事儿。
      return true;
    }
    return false;
  }
}
