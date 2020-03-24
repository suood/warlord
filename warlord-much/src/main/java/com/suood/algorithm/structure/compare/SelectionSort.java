package com.suood.algorithm.structure.compare;

import java.util.Random;

public class SelectionSort {
  int [] intArray ;
  public SelectionSort( int t ){
    t= t<10?10:t;
    this.intArray = new int [t];
    Random random = new Random();
    for (int i = 0; i < t; i++) {
      intArray[i] =random.nextInt(1024);
    }
  }

}
