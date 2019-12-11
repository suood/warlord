package com.suood.algorithm.skiplist;

import java.util.ArrayList;
import java.util.List;

public class SkipListNode<E> {

  private E value;
  public List<SkipListNode<E>> nextNodes;

  public E getValue() {
    return value;
  }

  public SkipListNode(E value) {
    this.value = value;
    nextNodes = new ArrayList<SkipListNode<E>>();
  }

  public int level() {
    return nextNodes.size() - 1;
  }

  public String toString() {
    return "SLN: " + value;
  }
}