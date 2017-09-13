package com.suood.concurrent.executors;

/**
 * Created by Alexander on 2017/3/31.
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
//        Thread t = new Thread();
//        System.out.println(t.getId());
//        System.out.println(t.getName());
//        System.out.println(t.getThreadGroup());

        System.out.println(Integer.SIZE);
        System.out.println(Integer.BYTES);
        int COUNT_BITS = Integer.SIZE - 3;
        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
        int STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);


    }
}
