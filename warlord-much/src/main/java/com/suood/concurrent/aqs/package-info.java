package com.suood.concurrent.aqs;
// MARK 这个包主要是 AQS姿势 及相关实践
/**
 // MARK ReentrantLock 排他锁 是一种悲观锁 Exclusive  [ɪkˈskluːsɪv]  kind of mutex lock
1 AbstractQueuedLongSynchronizer 继承自 AbstractOwnableSynchronizer
 AbstractOwnableSynchronizer 排他锁 须实现
 <code> private transient Thread exclusiveOwnerThread; </code> 记录了占有锁的线程
2 CLH 变种队列 非自旋 而是 阻塞状态。 prv&next
 条件队列是 单向队列 nextWaiter(prv = null & next =null) -> nextWaiter ((prv = null & next =null))

3 Fair 公平锁 ->直接排队
 NoneFair 非公平锁->看是否可以直接获取，获取不到再排队。
4
5
 */
