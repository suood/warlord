package com.suood.concurrent.aqs.condition;
/**

 // MARK BlockingQueue 根据锁等问题开搞。

 <code>
 public ArrayBlockingQueue(int capacity, boolean fair) {
   if (capacity <= 0)
    throw new IllegalArgumentException();
   this.items = new Object[capacity];
   lock = new ReentrantLock(fair);
   notEmpty = lock.newCondition();
   notFull =  lock.newCondition();
 }
 </code>
 */