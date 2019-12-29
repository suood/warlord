package com.suood.patterns.singleton;

import java.io.Serializable;

/**
 * Created by Alexander on 2017/3/10.
 * double check
 * 反序列化会有问题    被序列化的对象反序列化回来之后就不是原来的对象了。
 */
public class HungrySingleton implements Serializable , Cloneable {

  /**
   * 对象的创建三步
   * 1 分配内存  。
   * 2 实例化对象 。
   * 3 将引用指向内存。
   * volatile 关键字防止以上步骤的指令冲排序造成获取到null。
   */
  private static volatile   HungrySingleton instance = null;
    private HungrySingleton(){

    }
    public static  HungrySingleton getIntance(){
      if (instance == null) {
        synchronized (HungrySingleton.class) {
          if (instance == null) {
            instance = new HungrySingleton();
          }
        }
      }
      return instance;
    }
   
   protected Object readResolve(){    //解决序列化问题
     return  getIntance();
   }
   protected Object clone()throws CloneNotSupportedException{
      //禁止克隆
     throw new CloneNotSupportedException();
   }
}
