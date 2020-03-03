package com.suood.concurrent.extentions;

import java.util.concurrent.Callable;

public class DemoCallable implements Callable<Object> {

  @Override
  public Object call() throws Exception {
    System.out.println("implements Callable");
    return null;
  }
}
