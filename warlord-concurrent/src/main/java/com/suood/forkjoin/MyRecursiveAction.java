package com.suood.forkjoin;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

    private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<MyRecursiveAction> subtasks = Lists.newArrayList();
            subtasks.addAll(createSubtasks());
            for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subTasks =Lists.newArrayList();
        MyRecursiveAction subTask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subTask2 = new MyRecursiveAction(this.workLoad / 2);
        subTasks.add(subTask1);
        subTasks.add(subTask2);

        return subTasks;
    }

}