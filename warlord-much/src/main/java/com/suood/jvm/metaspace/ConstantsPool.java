package com.suood.jvm.metaspace;

// MARK "asb" + 1 这种操作会进入常量池，导致 metaspace 渐渐内存紧张产生fullgc.
// FIXME 
public class ConstantsPool {

  
  public static void main(String[] args) {
    String a = "abc";
    String b = "abc";
    String c = "abc";

    String aa = new String("abc");
    aa = aa.intern();// 获取常量池重的string
    String bb = new String("abc");
    String cc = new String("abc");

    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(b == c);
    System.out.println(a.equals(b));
    System.out.println(a.equals(c));
    System.out.println(b.equals(b));
    System.out.println(b.equals(aa));
  }
}
