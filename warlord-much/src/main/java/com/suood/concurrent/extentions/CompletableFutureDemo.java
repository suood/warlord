package com.suood.concurrent.extentions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

    CompletableFuture<String> future = completableFuture.thenApplyAsync(s -> s + " World");

    Thread.sleep(10000);
    if (future.isDone()){
      try {
        System.out.println(future.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
  } 
}
