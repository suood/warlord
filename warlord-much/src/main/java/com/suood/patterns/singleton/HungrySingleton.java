package com.suood.patterns.singleton;

/**
 * Created by FENGCUIJIE on 2017/3/10.
 */
public class HungrySingleton {

    private static  HungrySingleton instance = null;
    private HungrySingleton(){

    }
    public static synchronized HungrySingleton getIntance(){
            if (instance==null)
                return new HungrySingleton();
            else
                return  instance;

    }
}
