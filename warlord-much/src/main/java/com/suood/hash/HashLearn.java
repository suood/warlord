package com.suood.hash;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class HashLearn {
  //hash map获取 hash值的方式
  static final int hash(Object key) {
    int h;
    System.out.println((h = key.hashCode()));
    System.out.println((h >>> 16));
    System.out.println((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  public static void main(String[] args) {
//      String key = "test";
      //String 类对象的 hash码只会计算一次，计算完成之后就会缓存。 Integer类型的hash码就是其值本身，所以这两种数据操作哈希其实是很快的。
    //Long 类型是 (int)(value ^ (value >>> 32)) 值本身与 本身右移32位后的值 按位异或
//      hash(key);
    //移位操作
//    System.out.println(2<<12);
//    System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
//    System.out.println(Long.toBinaryString((Long.MAX_VALUE*31)>>>31).length());
//    System.out.println(Long.toBinaryString(Long.MAX_VALUE^(Long.MAX_VALUE*31)>>>31).length());

//    HashMap map = Maps.newHashMap();
   
//    System.out.println("111");
//    Random random = new Random();
//    random.nextInt(10000000);
//    for (int i = 0; i <1000 ; i++) {
//                Integer      key =random.nextInt(10000000);
//                int h;
//      System.out.println(((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)) & 255) ;
//    }
//    IntStream.range(1,10000).forEach(value -> {
//      System.out.print(value);
//      System.out.print("-->");
//      System.out.println(value&255);
//    });


         int cap = 1<< 30 ;
    int n = cap - 1;
    System.out.println(Integer.toBinaryString(n));
    System.out.println(Integer.toBinaryString(n >>> 1));
    n |= n >>> 1;
    System.out.println(n >>> 1);
    n |= n >>> 2;
    System.out.println( n >>> 2);
    n |= n >>> 4;
    System.out.println(n >>> 4);
    n |= n >>> 8;
    System.out.println(n >>> 8);
    n |= n >>> 16;
    System.out.println(n >>> 16);
    System.out.println(Integer.toBinaryString(Integer.MAX_VALUE + 1));  //因吹斯汀！
    System.out.println(Integer.toBinaryString(~Integer.MAX_VALUE ));  //因吹斯汀！
    System.out.println(1 << 30); //2的30次幂
    

  }
}
