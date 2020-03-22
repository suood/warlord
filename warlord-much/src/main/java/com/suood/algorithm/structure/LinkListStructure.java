package com.suood.algorithm.structure;

import java.math.BigDecimal;
import lombok.Getter;

/**
 * 单向链表，非线程安全,每次向尾部添加
 *
 * @param <T> Type of LinkListStructure
 */
public class LinkListStructure<T> {

  @Getter
  private Node<T> tail;
  @Getter
  private Node<T> head;
  @Getter
  private int size;
  @Getter
  private int capacity;

  /**
   * 构造方法
   */
  public LinkListStructure() {
    this.tail = null;
    this.size = 0;
    this.capacity = BigDecimal.TEN.intValue();
  }

  public LinkListStructure(int capacity) {
    this.tail = null;
    this.size = 0;
    this.capacity = capacity;
  }

  class Node<T> {

    private Node<T> next;
    private Node<T> previous;
    private Object value;

    Node(T t) {
      value = t;
    }
  }

  /**
   * 增加新的节点数据 需要知道队尾在哪里（引入tail）
   *
   * @param t
   * @return
   */
  public boolean add(T t) {
    if (null == tail) {
      head = tail = new Node<T>(t);
      size++;
      return true;
    }
    if (tail != null && size < capacity) {
      Node n = new Node<T>(t);
      n.previous = tail;
      tail = tail.next = n;
      size++;
    }
    return false;
  }

  /**
   * 移除一个指定数值的数据 （需要从头开始寻找 引入head）
   *
   * @param t
   * @return
   */
  public boolean remove(T t) {
    if (null == head && null == tail) {
      return false;
    }
    Node pointer = head;
    while (pointer != null) {
      if (pointer.value.equals(t)) {

        if (pointer.previous != null) {
          pointer.previous.next = pointer.next;
        }
        if (pointer.next != null) {
          pointer.next.previous = pointer.previous;
        }
        if (tail.equals(pointer)) {
          tail = pointer.previous;
        }
        if (head.equals(pointer)) {
          head = pointer.next;
        }
        size--;
        return true;
      } else {
        pointer = pointer.next;
      }
    }
    return false;
  }

  public boolean contain(T t) {
    Node a = head;
    while (a != null) {
      if (a.value.equals(t)) {
        return true;
      } else {
        a = a.next;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    LinkListStructure<Integer> list = new LinkListStructure<Integer>(10);
    for (int i = 0; i < 10; i++) {
      list.add(new Integer(i));
    }
    list.remove(new Integer(9));
    System.out.println(list.contain(new Integer(9)));
  }
}
