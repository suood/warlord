package com.suood.curator.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Alexander on 2017/3/20.
 */
public class DistributedBarrierExample {

  public static final String CONNECTION_STRING = "118.190.40.207:2181,139.129.227.18:2181,114.215.16.32:2181";
  public static final int QTY = 3;
  private static final String PATH = "/examples/barrier";

  public void example() throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECTION_STRING, new ExponentialBackoffRetry(1000, 3));
    client.start();

    ExecutorService service = Executors.newFixedThreadPool(QTY);
    DistributedBarrier controlBarrier = new DistributedBarrier(client, PATH);
    controlBarrier.setBarrier();

    for (int i = 0; i < QTY; ++i) {
      final DistributedBarrier barrier = new DistributedBarrier(client, PATH);
      final int index = i;
      Callable<Void> task = new Callable<Void>() {
        @Override
        public Void call() throws Exception {

          barrier.waitOnBarrier();
          return null;
        }
      };
      service.submit(task);
    }

    Thread.sleep(10000);
    System.out.println("all Barrier instances should wait the condition");

    controlBarrier.removeBarrier();

    service.shutdown();
    service.awaitTermination(10, TimeUnit.MINUTES);
  }


  public static void main(String[] args) {
    DistributedBarrierExample distributedBarrierExample = new DistributedBarrierExample();
    try {
      distributedBarrierExample.example();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
