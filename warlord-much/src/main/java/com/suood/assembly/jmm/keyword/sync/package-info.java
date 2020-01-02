package com.suood.assembly.jmm.keyword.sync;
//同步 synchronized
// MARK XX:PreBlockSpin 开启自旋锁

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
// HashCode   保存从对象头拷贝过来的HashCode值（可能还包含GC分代年龄）
// Candidate 候选  0 为不必唤醒的线程，1为被阻塞的线程

 */
// MARK synchronized 关键字经过了1.6版本的优化具体内容有
/**
 锁等级标记在了对象头上面。
 
 适应性自旋（Adaptive Spinning）、
 锁消除（Lock Eliminate）、
 锁粗化（Lock Coarsening）、
 轻量级锁（Lightweight Locking）
 偏向锁（Biased Locking）
// MARK  锁消除（Lock Eliminate）
  当编译器经过逃逸分析之后发现无需使用 监视器方式实现的化会将监视器消除掉
 例如：
  public synchronized int testa(){
    int i=1;
    int b=1;
    int c =i+b;
   return c;
 }
 1.5会变为StringBuffer#append() //同步的
 1.5之后汇编为 StringBuilder#append() //非同步的。
 public static String test03(String s1, String s2, String s3) {
 String s = s1 + s2 + s3;
 return s;
 }

 // MARK 锁粗化
 public static String test04(String s1, String s2, String s3) {
 StringBuilder sb = new StringBuilder();
 sb.append(s1);
 sb.append(s2);
 sb.append(s3);
 return sb.toString();
 }
 会被转变为 sb.append().append().append() 只需要加一个锁就ok了。
 */


/**
 // MARK 锁膨胀   不可逆
 MARK WORD
 MarkOop.hpp ->hotspot 源码
 在对象头中（Object Header）存在两部分。
 第一部分用于存储对象自身的运行时数据，HashCode、GC Age、锁标记位、是否为偏向锁。等。
 一般为32位或者64位（视操作系统位数定）。官方称之为Mark Word，它是实现轻量级锁和偏向锁的关键。
 另外一部分存储的是指向方法区对象类型数据的指针（Klass Point），如果对象是数组的话，还会有一个额外的部分用于存储数据的长度。
  nolock->偏向锁 ->spinlock ->light lock  ->heavylcok(system level 涉及到用户态到内核态切换)
 // MARK 启用参数:
 -XX:+UseBiasedLocking
 // MARK  关闭延迟:
 -XX:BiasedLockingStartupDelay=0
 // MARK  禁用参数:
 -XX:-UseBiasedLocking
 */