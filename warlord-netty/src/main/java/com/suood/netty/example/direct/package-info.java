package com.suood.netty.example.direct;
// MARK zero copy 输出到 堆外内存    -XX:MaxDirectMemorySize
// 默认大小 private static long directMemory = 64 * 1024 * 1024;
// 直接内存的对象被销毁后会触发 Deallocator 的清除内存的函数。清理掉内存。