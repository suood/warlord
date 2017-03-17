package com.suood.patterns.singleton;

/**
 * Created by FENGCUIJIE on 2017/3/10.
 */
public class LazySingleton {
    public static LazySingleton instance = new LazySingleton();

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        return  instance;
    }

}