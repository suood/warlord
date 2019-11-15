package com.suood.patterns.singleton;

/**
 * Created by Alexander on 2017/3/10.
 * double check
 * 反序列化会有问题
 */
public class HungrySingleton {

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

}
