package com.suood.netty.example.direct;

import java.nio.ByteBuffer;

// MARK 直接内存分配慢，读写快。 jvm内存分配快，读写慢。
public class DirectBufferDemo {

  public static void main(String[] args) {

    // 直接分配  new DirectByteBuffer(1024); jvm native  -> malloc 标准c 函数申请内存
    ByteBuffer.allocateDirect(1024);
    // new HeapByteBuffer
    ByteBuffer.allocate(1024); //堆内存分配
  }

}
