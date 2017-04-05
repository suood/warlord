package com.suood.concurrent.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by FENGCUIJIE on 2017/3/31.
 */
public class ForkJoinDemo {

    public ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
//    public BlockingQueue queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo();
        forkJoinDemo.pool.getPoolSize();
//        Thread t = new Thread();
//        System.out.println(t.getId());
//        System.out.println(t.getName());
//        System.out.println(t.getThreadGroup());

//        System.out.println(Integer.SIZE);
//        System.out.println(Integer.BYTES);
//        int COUNT_BITS = Integer.SIZE - 3;
//        int RUNNING    = -1 << COUNT_BITS;
//        int SHUTDOWN   =  0 << COUNT_BITS;
//        int STOP       =  1 << COUNT_BITS;
//        int TIDYING    =  2 << COUNT_BITS;
//        int TERMINATED =  3 << COUNT_BITS;
//        System.out.println(RUNNING);
//        System.out.println(SHUTDOWN);
//        System.out.println(STOP);
//        System.out.println(TIDYING);
//        System.out.println(TERMINATED);
        System.out.println(1 << 13);
        System.out.println(0xf0000000);


    }
}
