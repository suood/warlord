package com.suood.warlord.demo.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KissPrettyGirl {

  @Autowired
  private PrettyGirl prettyGirl;

  @PostConstruct
  public void wireCondom() {
    System.out.println("Kiss pretty girl 4 say hi");
  }

  @PreDestroy
  public void comeInMouth() {
    System.out.println("Kiss pretty girl 4 bye ");
  }
}
