package com.suood.common;

//copy from jsl 17
//不要随意再多线程中变动字节数组的内容。
public class WordTearingDemo extends Thread {

  static final int LENGTH = 8;
  static final int ITERS = 1000000;
  static byte[] counts = new byte[LENGTH];
  static Thread[] threads = new Thread[LENGTH];

  final int id;

  WordTearingDemo(int i) {
    id = i;
  }

  public void run() {
    byte v = 0;
    for (int i = 0; i < ITERS; i++) {
      byte v2 = counts[id];
      if (v != v2) {
        System.err.println("Word-Tearing found: " +
            "counts[" + id + "] = " + v2 +
            ", should be " + v);
        return;
      }
      v++;
      counts[id] = v;
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < LENGTH; ++i) {
      (threads[i] = new WordTearingDemo(i)).start();
    }
  }
}