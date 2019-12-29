package com.suood.assembly;
//输出汇编用于学习JMM模型
//-Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
//查看java字节码    javap -v AssemblyAdd.class(带输出附加信息)                 javap -c AssemblyAdd.class （对代码进行反汇编）
// MARK 本包主要用于研究JMM的实验和学习。
 // i++ 　>load  iinc  
/** cpu级别缓存 和 主存的交互操作有
 * lock
 * unlock
 * read
 * load
 * store
 * use
 * assign
 * write
 */
/**
无 volatile

1。Thread_a  ->获取变量 µ --l1缓存或者寄存器中（栈帧），开始运行进行循环
 read -> load -> use
2。Thread_b  ->获取变量 µ --l1缓存或者寄存器中（栈帧），开始运行并赋值
 read -> load -> use -> assign-> write(暂存于缓存行，通过最少使用等算法慢慢同步回主要内存)
// MARK 以上线程虽然使用了同一个变量  µ 但由于 硬件底层架构的原因，彼此并不可见

 */

/**
  volatile

 1。Thread_a  ->获取变量 µ --l1缓存或者寄存器中（栈帧），开始运行进行循环
 read -> load -> use
 2。Thread_b  ->获取变量 µ --l1缓存或者寄存器中（栈帧），开始运行并赋值
 （lock lock在变更之前） read -> load -> use -> assign （lock 之后 put回主存 汇编语言级别）->store->write（unlock）
其中read 和load 是成对出现的   store 和write是成对出现的。
 原子性操作基于 cmp chxg 汇编 指令
 以上线程使用了同一个变量  µ
            // MARK    （如果数据长度超过缓存行大小 默认是64 bit 会锁住 总线）
 */
/**
 * 指令重排序 as if serial 不影响 最终执行结果。这个编译原理级别的规约。（可参见 龙书 或者 虎书）即单线程重排不影响结果
 * 指令冲排序发生在 JIT（just in time）时，和 底层cpu执行 汇编语言时 所以有两个阶段
 * 冲排序可能会导致程序结果失幂等性，多次运行结果不一致。常见的如，多线程重的赋值和使用。
 */
