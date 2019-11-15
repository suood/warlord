package com.suood.patterns.singleton;

/**
 * 虚拟机保证唯一性 
 */
public enum  EnumSigleton {
  INSTANCE;
  int value;
  public int getValue() {
    return value;
  }
  public void setValue(int value) {
    this.value = value;
  }
}
