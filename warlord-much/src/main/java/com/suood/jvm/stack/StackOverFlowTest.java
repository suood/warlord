package com.suood.jvm.stack;

import javax.swing.table.TableRowSorter;

public class StackOverFlowTest {
    private int stackLength = 1;

    public void stackLengthPlus(){
        stackLength ++;
        stackLengthPlus();
    }

    public static void main(String[] args) {
        StackOverFlowTest test = new StackOverFlowTest();
        try {
            test.stackLengthPlus();
        }catch (Throwable e){
            System.out.println("stackLength="+test.stackLength);
            e.printStackTrace();
        }
    }
}
