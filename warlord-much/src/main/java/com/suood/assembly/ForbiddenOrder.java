package com.suood.assembly;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForbiddenOrder {
 volatile int a =0;
//  volatile int a;      字节码
//  descriptor: I
//  flags: ACC_VOLATILE

  //  volatile 保证了可见性，但并没有保证原子性
//2,2,2,2,5,7,8,9,9,10,11,10,13,12,11,11,18,18,20,19,22,18,22,24,26,21,27,28,30,21,20,33,30,34,35,29,26,38,38,37,36,36,34,33,45,46,47,48,45,44,40,53,54,55,56,40,57,59,60,39,60,62,64,65,59,58,50,69,49,70,71,73,46,75,76,77,77,74,73,81,72,65,84,84,86,87,63,63,89,62,91,88,87,86,86,82,80,79,79,75,
  public void giveValue() {
    a += 1;
    
  }

  

  public static void main(String[] args) throws InterruptedException {
    ForbiddenOrder forbiddenOrder = new ForbiddenOrder();

      Thread t = new Thread(() -> forbiddenOrder.giveValue());
     t.sleep(100l);
     t.start();

    
    System.out.println(forbiddenOrder.a);
  }
}
