package com.suood.forkjoin;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class MyRecursiveTask extends RecursiveTask<Long> {

    private long workLoad = 0;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    protected Long compute() {

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            List<MyRecursiveTask> subtasks = Lists.newArrayList();
            subtasks.addAll(createSubtasks());

            for(MyRecursiveTask subtask : subtasks){
                subtask.fork();
            }
            subtasks.stream().forEach(e->e.fork());
            subtasks.parallelStream().forEach(e->e.fork());
            Long result = 0l;
            for(MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result ;
//          return   subtasks.stream().collect(Collectors.summingLong(e->e.join()));
//          return   subtasks.parallelStream().collect(Collectors.summingLong(e->e.join()));

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }

    private List<MyRecursiveTask> createSubtasks() {
        List<MyRecursiveTask> subtasks =  Lists.newArrayList();

        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

    public static void main(String[] args) {
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(2048);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);

        System.out.println("mergedResult = " + mergedResult);
    }
}
