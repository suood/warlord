package com.suood.concurrent.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

  public static void main(String[] args) {
    // MARK 同时只允许一个线程使用        new Semaphore(1） 使用非公平锁方式创建信号量
    Semaphore semaphore  = new Semaphore(1,true);
    for (int i = 0; i <20 ; i++) {
      final int z = i;
      new Thread(() -> {
        try {
          System.out.println(semaphore.availablePermits());
          semaphore.acquire();
          TimeUnit.SECONDS.sleep(1l);
          semaphore.release();
          System.out.print("  " +z+"   "+semaphore.availablePermits());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}
