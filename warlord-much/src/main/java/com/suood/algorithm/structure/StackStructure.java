package com.suood.algorithm.structure;

import lombok.Getter;

public class StackStructure<T> {

  @Getter
  private int size;
  @Getter
  private int capacity;
  private Element top;

  class Element<T> {

    private T t;
    private Element<T> next;
  }

  public Element push(T t) {
    Element e = new Element();
    e.t = t;
    if (top == null) {
      top = e;
      size++;
    } else {
      e.next = top;
      top = e;
    }
    return top;
  }

  public <T> T pop() {
    if (size==0)return null;
    Element e = top;
    top = e.next;
    size--;
    return (T)e.t;
  }
}
