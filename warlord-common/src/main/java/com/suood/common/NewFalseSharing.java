package com.suood.common;// JDK 8及以上支持
//@sun.misc.Contended  //JDK8       //配合参数 -XX:-RestrictContended

//@Contended


import sun.misc.Contended;


public class NewFalseSharing {
  @Contended
  private volatile long usefulVal;
}