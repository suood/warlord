package com.suood.communication;

/**
 * Created by FENGCUIJIE on 2017/2/26.
 */
public class TraditionalThreadCommunication
{
    public static void main(String[] args)
    {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run()
            {
                for (int i = 1; i <= 50; i++)
                {
                    business.sub(i);
                }
            }
        }).start();
        for (int i = 1; i <= 50; i++)
        {
            business.busi(i);
        }
    }
}

class Business
{
    private boolean bShouldSub= true;

    public synchronized void sub(int i)
    {
        while (!bShouldSub)
        {
            try
            {
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++)
        {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void busi(int i){
        while (bShouldSub){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++)
        {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        this.notify();
    }
}