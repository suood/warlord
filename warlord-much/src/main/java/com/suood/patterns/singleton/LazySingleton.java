package com.suood.patterns.singleton;

/**
 * Created by Alexander on 2017/3/10.
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
