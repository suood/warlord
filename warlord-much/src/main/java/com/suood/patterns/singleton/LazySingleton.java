package com.suood.patterns.singleton;

/**
 * Created by Alexander on 2017/3/10.
 *  反序列化会有问题    被序列化的对象反序列化回来之后就不是原来的对象了。
 */
public class LazySingleton {
//    public static final LazySingleton instance = new LazySingleton();
    private static final LazySingleton instance = new LazySingleton();

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        return  instance;
    }

}
