package com.suood.deepin;

//伪共享
public class FalseSharing {
    public volatile long usefulVal;
    public volatile long t1, t2, t3, t4, t5, t6, t7;
    public long preventOptmisation(){
        return t1 + t2 + t3 + t4 + t5 + t6 + t7;
    }
}
