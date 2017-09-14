package com.suood.jvm.stack;

/**
Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
        at java.lang.Thread.start0(Native Method)
        at java.lang.Thread.start(Thread.java:714)
        at com.suood.jvm.stack.StackOOMTest.oom(StackOOMTest.java:17)
        at com.suood.jvm.stack.StackOOMTest.main(StackOOMTest.java:23)
        Java HotSpot(TM) 64-Bit Server VM warning: Exception java.lang.OutOfMemoryError occurred dispatching signal SIGINT to handler- the VM may need to be forcibly terminated

*/
// VM args -Xss2M

public class StackOOMTest {
    public void  notStop(){
        while (true){

        }
    }
    public void oom(){
        while (true){
            Thread thread  = new Thread(new Runnable() {
                @Override
                public void run() {
                    notStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOOMTest test = new StackOOMTest();
        test.oom();
    }
}
