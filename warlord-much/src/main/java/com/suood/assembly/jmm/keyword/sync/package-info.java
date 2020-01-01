package com.suood.assembly.jmm.keyword.sync;
//同步 synchronized


// 每个对象 创建之后都会有一个monitor对象 我们将它叫做监视器对象，或者叫做管程。 monitor 是可以重入的排他锁。
//自旋-偏向->公平->重量级锁。
/** 在系统级别，重量级锁 mutex lock ->互斥变量
 竞争临界资源的线程在竞争失败后会进入一个waitset中，当前线程释放了资源之后，系统会去唤醒等待线程，如果等待线程过多，
 会出现惊群效应，多个线程同时去争抢资源，造成不必要的开销。 所以引入了一个候选机制
 Monitor Record 中存在
// Owner 当前持有资源的线程id。初始值和被释放后的值都是 null 被持有时记录持有线程的id
// MARK EntryQ 关联一个系统级别的互斥锁 mutex semaphore 每次进入+1 每次离开-1
// RcThis 表示blocked或者waiting在该Monitor Record上所有的线程的个数
// MARK Nest 用来实现重入锁的计数
// Candidate 候选  0 为不必唤醒的线程，1为被阻塞的线程

 */