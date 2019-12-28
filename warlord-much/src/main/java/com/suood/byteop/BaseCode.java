package com.suood.byteop;

public class BaseCode {

  public static void main(String[] args) {
    int  b = 7 ;
    System.out.println(Integer.toBinaryString(~b+1));//11111111111111111111111111111001
    System.out.println(~b+1); //-7
    int a =-9 ;
    System.out.println(Integer.toBinaryString(~a+1)); //1001
    System.out.println(~a+1);//9
    int c =-0;
    System.out.println(Integer.toBinaryString(~c+1)); //0
  }
}
