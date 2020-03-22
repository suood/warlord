package com.suood.algorithm.structure;


public class StackArrayStructure<T> {

  private T[] objectArray;
  private int top = -1;
  private int capacity = 10;

  public boolean push(T t) {
    if (top == capacity - 1) {
      return false;
    }

    top++;
    objectArray[top] = t;
    return true;
  }

  public <T> T pop() {
    if (top == -1) {
      return null;
    } else {
      return (T) objectArray[top];
    }
  }
}
