package com.suood.concurrent.aqs.condition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueDemo {
  // MARK 是否公平锁 创建队列          ReentrantLock lock
  private static ArrayBlockingQueue <Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(1,true);

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        int i = 0;
        while (true)   {
          try {
            System.out.println( i + " put into queue" );
            arrayBlockingQueue.put(i);

            i++;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }) ;
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
          Integer item =   arrayBlockingQueue.take();
            System.out.println( item + " take from queue" );
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }
}
