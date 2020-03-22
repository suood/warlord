package com.suood.algorithm.structure;



public class StackArrayStructure<T> {

  private Object[] objectArray;
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
      T t = (T) objectArray[top];
       objectArray[top] =null;
      top --;
      return t;
    }
  }
  public StackArrayStructure(int capacity){

    this.objectArray = new Object[capacity];
    this.capacity = capacity;
  }

  public static void main(String[] args) {
    StackArrayStructure<Integer> stackArrayStructure = new StackArrayStructure<Integer>(5);
    stackArrayStructure.push(new Integer(1));
    stackArrayStructure.push(new Integer(2));
    System.out.println(stackArrayStructure.pop().toString());
    System.out.println(stackArrayStructure.pop().toString());
  }
}
