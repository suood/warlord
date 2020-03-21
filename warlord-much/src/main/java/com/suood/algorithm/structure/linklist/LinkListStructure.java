package com.suood.algorithm.structure.linklist;

import java.math.BigDecimal;

/**
 * 链表，非线程安全
 * @param <T> Type of LinkListStructure
 */
public class LinkListStructure<T> {

  private Node<T> current;
  private Node<T> head;
//  private Node<T> tail;
  private int size;
  private  int capacity;

  /**
   * 构造方法
   */
  public LinkListStructure() {
    this.current = null;
//    this.head = null;
//    this.tail = null;
    this.size = 0;
    this.capacity = BigDecimal.TEN.intValue();
  }
  public LinkListStructure(int capacity) {
    this.current = null;
    this.size = 0;
    this.capacity = capacity;
  }

  class Node<T> {
    private Node<T> next;
    private T object;
    Node(T t) {
      object = t;
    }
  }
}
