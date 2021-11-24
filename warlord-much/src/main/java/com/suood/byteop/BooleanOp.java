package com.suood.byteop;

/**
 * @author suguangqiang .
 * @date 2021-11-23 17:51.
 */
public class BooleanOp {
  public static void main(String[] args) {
    System.out.println(16 &~ 0X40);
    System.out.println(~0X40);
    System.out.println(1023 &~ 0X40);
    System.out.println(1&~3);
  }
}
