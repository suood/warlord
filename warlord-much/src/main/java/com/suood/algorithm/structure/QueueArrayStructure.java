package com.suood.algorithm.structure;

import java.util.Optional;

public class QueueArrayStructure<T> {

  private Object[] objectArray;
  private int capacity;
  private int head = 0;
  private int tail = 0;
  private int size = 0;

  public QueueArrayStructure(int capacity) {
    objectArray = new Object[capacity];
  }

  public boolean enQueue(T t) {
    // 是否已经满了
    if (size == capacity) {
      return false;
    } else {
      objectArray[tail] = t;
      if (tail == capacity) {
        tail = 0;
      } else {
        tail++;
      }
      size++;
      return true;
    }
  }

  public T denQueue() {
    Object object = objectArray[head];
    if (null != object) {
      if (head == capacity - 1) {
        head = 0;
      } else {
        objectArray[head] = null;
        head++;
        size--;
      }
      return (T) object;
    } else {
      return null;
    }
  }
}
