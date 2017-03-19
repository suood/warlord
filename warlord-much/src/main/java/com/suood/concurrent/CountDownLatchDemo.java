package com.suood.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by FENGCUIJIE on 2017/3/20.
 *
 * CountDownLatch demo
 * 主线程在其他线程完成后继续执行
 */
public class CountDownLatchDemo {
    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args)  {
        CountDownLatch latch=new CountDownLatch(2);//两个工人的协作
        Worker worker1=new Worker("zhang san", 5000, latch);
        Worker worker2=new Worker("li si", 8000, latch);
        worker1.start();//
        worker2.start();//
        try {
            latch.await();//等待所有工人完成工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all work done at "+sdf.format(new Date()));
    }


    static class Worker extends Thread{
        String workerName;
        int workTime;
        CountDownLatch latch;
        public Worker(String workerName ,int workTime ,CountDownLatch latch){
            this.workerName=workerName;
            this.workTime=workTime;
            this.latch=latch;
        }
        public void run(){
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));
            try {
                Thread.sleep(workTime);
                //sleep 在真实开发中会被实际业务所代替
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                latch.countDown();//这里非常有必要放在finally里面执行，无论如何都会countDown
            }
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));

          //工人完成工作，计数器减一

        }


    }


}
