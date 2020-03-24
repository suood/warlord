package com.suood.algorithm.structure.compare;

import java.util.Random;

public abstract class AbstractSort {
  private Random random = new Random();
  public int [] intArray;
  abstract void doSort ();
  public void  printOrigin(){
    for (int i = 0; i <intArray.length ; i++) {
      System.out.print(intArray[i]);
      System.out.print(",");
    }
    System.out.println();
  }
  public AbstractSort (int num ){
    initArray(num);
  }
  private void  initArray(int num){
    this.intArray = new int [num];
    for (int i = 0; i < num; i++) {
      intArray[i] = random.nextInt(1024);
//      intArray[i] = random.nextInt(Integer.MAX_VALUE);
    }
  }
}
