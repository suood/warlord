package com.suood.deepin;

//伪共享

public class FalseSharing {
    public volatile long usefulVal;
    public volatile long t1, t2, t3, t4, t5, t6, t7;
    public long preventOptmisation(){
        return t1 + t2 + t3 + t4 + t5 + t6 + t7;
    }
    //填满 l1 缓存的一页内存

    //cpu 缓存 首先分段，分段之后分页。 如果多个对象共用同一页同一缓存行会造成伪共享 降低存取速度。

  // cpu1 cpu2                l1 第n行缓存分别存户 a ,b两个数值，cpu1 变更a 数据会向 l2 l3传播写入，
  // 此时 cpu2 会对自己使用的缓存做失效操作   同理 cpu2 写入b 那么 cpu1 也会做相应的动作。这样的操作极大的消耗了性能
  //如果一个缓存行存储了4个变量，同时被4个cpu操作，那么其性能可想而知。
    
}

@sun.misc.Contended
class NewFalseSharing {
     //配合参数 -XX:-RestrictContended
  private volatile  double usefulVal;
}
