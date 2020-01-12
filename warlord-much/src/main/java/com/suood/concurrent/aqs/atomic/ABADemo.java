package com.suood.concurrent.aqs.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ABA问题实例
 */
public class ABADemo {
   private static AtomicInteger atomicInteger = new AtomicInteger(1);
  public static void main(String[] args) {
          Thread thread1 =     new Thread(new Runnable() {
                @Override
                public void run() {
                  atomicInteger.incrementAndGet();
                }
              });
            Thread thread2 =  new Thread(new Runnable() {
                @Override
                public void run() {
                  atomicInteger.incrementAndGet();
                  atomicInteger.decrementAndGet();
                }
              }) ;
            thread1.start();
            thread2.start();
    try {
      thread2.join();
      thread1.join();  // MARK  thread1 无从得知 该述职是否曾经发生过变化
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(atomicInteger.get());
  }
}
