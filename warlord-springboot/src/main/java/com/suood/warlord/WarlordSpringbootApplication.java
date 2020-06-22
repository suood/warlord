package com.suood.warlord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.suood"})
@SpringBootApplication
public class WarlordSpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(WarlordSpringbootApplication.class, args);
  }

}
