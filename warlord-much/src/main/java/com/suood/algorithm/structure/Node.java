package com.suood.algorithm.structure;

import lombok.Getter;

public class Node<T> {

  @Getter
  private Node<T> next;
  @Getter
  private Node<T> previous;
  @Getter
  private Object value;

  Node(T t) {
    value = t;
  }
}