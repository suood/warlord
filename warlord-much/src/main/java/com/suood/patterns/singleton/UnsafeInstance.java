package com.suood.patterns.singleton;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeInstance {

  private static volatile Unsafe unsafe;

  private UnsafeInstance() {
    //private 
  }

  public static Unsafe getUnSafe() {
      if (null == unsafe) {
        synchronized (UnsafeInstance.class) {
         if (null == unsafe) {
           try {
             Field f = Unsafe.class.getDeclaredField("theUnsafe");
             f.setAccessible(true);
             unsafe = (Unsafe) f.get(null);
           } catch (Exception e) {
             e.printStackTrace();
           }
         }
        }
      }
      return unsafe;

  }
}
