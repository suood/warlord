package com.suood.algorithm.structure;


public class QueueArrayStructure<T> {

  private Object[] objectArray;
  private int capacity;
  private int head = 0;
  private int tail = 0;
  private int size = 0;

  public QueueArrayStructure(int capacity) {
    this.capacity = capacity;
    objectArray = new Object[capacity];
  }

  public boolean enQueue(T t) {
    // 是否已经满了 当剩余最后一个空位时，无法使用。
    if (size == (capacity - 1)) {
      return false;
    } else {
      objectArray[tail] = t;
      if (tail == capacity - 1) {
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

  public static void main(String[] args) {
    QueueArrayStructure<Integer> queueArrayStructure = new QueueArrayStructure<Integer>(10);
    for (int i = 0; i < 10; i++) {
      System.out.println( queueArrayStructure.enQueue(i));
    }
    System.out.println("enQueue");
    for (int i = 0; i < 10; i++) {
      System.out.println(queueArrayStructure.denQueue());
    }
    System.out.println("denQueue");
  }
}
