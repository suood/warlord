package com.suood.algorithm.structure;

// MARK 当链表不限制容量时，会出现内存溢出。
public class QueueLinkedListStructure<T> {

  private Node<T> head;
  private Node<T> tail;
  private int capacity = 10;

  class Node<T> {

    Node<T> next;
    T t;

    public Node(T t) {
      this.t = t;
    }
  }

  public QueueLinkedListStructure(int capacity) {
    this.head = this.tail = null;
    this.capacity = capacity > 0 ? capacity : 10;

  }

  public boolean enQueue(T t) {
    Node<T> node = new Node<>(t);
    if (null == head && null == tail) {
      head = tail = node;
    } else if (head.equals(tail)) {
      head.next = tail = node;
    } else {
      tail = tail.next = node;
    }

    return true;
  }

  public T denQueue() {
    if (null == head) {
      return null;
    }
    Node<T> returnNode = head;
    if (head.equals(tail)) {
      head = tail = null;
    } else {
      head = head.next;
    }

    return returnNode.t;
  }

  public static void main(String[] args) {
    QueueLinkedListStructure<Integer> queueLinkedListStructure = new QueueLinkedListStructure<>(10);
    for (int i = 0; i < 10; i++) {
      queueLinkedListStructure.enQueue(i);

    }
    System.out.println(queueLinkedListStructure);
    for (int i = 0; i < 10; i++) {
      queueLinkedListStructure.denQueue();
    }
    System.out.println(queueLinkedListStructure);
  }
}
