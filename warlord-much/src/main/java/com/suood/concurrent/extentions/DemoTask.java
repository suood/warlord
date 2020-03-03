package com.suood.concurrent.extentions;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class DemoTask extends FutureTask {

  public DemoTask(Callable callable) {
    super(callable);
  }

  public DemoTask(Runnable runnable, Object result) {
    super(runnable, result);
  }
}
