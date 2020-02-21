package com.suood.concurrent.executors;
// MARK 用户线程->系统线程 线程创建，切换（保存运行状态），销毁 开销巨大。 引入线程池概念。 内核空间有限而且宝贵，
// 不可以无限制的创建，否则在竞争和切换的过程中开销更大。
/**
   <code>public class ThreadPoolExecutor extends AbstractExecutorService</code>
 // MARK   构造方法参数注释
 <p>
 java.util.concurrent.ThreadPoolExecutor public ThreadPoolExecutor(int corePoolSize,
 int maximumPoolSize,
 long keepAliveTime,
 TimeUnit unit,
 BlockingQueue<Runnable> workQueue)
 Creates a new ThreadPoolExecutor with the given initial parameters and default thread factory and rejected execution handler. It may be more convenient to use one of the Executors factory methods instead of this general purpose constructor.

 Params:
 corePoolSize – the number of threads to keep in the pool, even if they are idle, unless allowCoreThreadTimeOut is set
 maximumPoolSize – the maximum number of threads to allow in the pool
 keepAliveTime – when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
 unit – the time unit for the keepAliveTime argument
 workQueue – the queue to use for holding tasks before they are executed. This queue will hold only the Runnable tasks submitted by the execute method.
 Throws:
 IllegalArgumentException – if one of the following holds: corePoolSize < 0 keepAliveTime < 0 maximumPoolSize <= 0 maximumPoolSize < corePoolSize
 NullPointerException – if workQueue is null
 </p>

 <code>public abstract class AbstractExecutorService implements ExecutorService</code>


 */