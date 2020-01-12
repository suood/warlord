package com.suood.concurrent.aqs.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAFixDemo {

  private static AtomicStampedReference<Integer> atomicIntegerAtomicReference = new AtomicStampedReference<>(1,1);

  public static void main(String[] args) {
    atomicIntegerAtomicReference.set(10,2);
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(1l);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
       boolean result =  atomicIntegerAtomicReference.compareAndSet(10,11,2,3);
        System.out.println( "Thread is change  " + result);
      }
    });
    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        atomicIntegerAtomicReference.compareAndSet(10,11,2,3);
        atomicIntegerAtomicReference.compareAndSet(11,10,3,4);
        System.out.println("thread2 run ");
      }
    });
    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
