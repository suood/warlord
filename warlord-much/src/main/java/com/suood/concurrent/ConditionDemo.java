package com.suood.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Alexander on 2017/3/28.
 */
public class ConditionDemo {
    //显示创建偏向锁
    Lock lock = new ReentrantLock(false);
    Condition NOT_LOCK = lock.newCondition();
    Condition LOCK_UP = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo = new ConditionDemo();
//        if(conditionDemo.lock.tryLock() || conditionDemo.lock.tryLock(10,TimeUnit.MILLISECONDS)){
//            //do something
//        }
        conditionDemo.lock.lock();
        conditionDemo.lock.lock();
        conditionDemo.lock.lock();
//        conditionDemo.LOCK_UP.await(10, TimeUnit.SECONDS);
        conditionDemo.lock.unlock();
        conditionDemo.lock.unlock();
        conditionDemo.lock.unlock();
        System.out.println("1111");
    }
}
