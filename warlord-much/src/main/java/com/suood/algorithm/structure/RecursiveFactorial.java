package com.suood.algorithm.structure;

/**
 * 递归实现阶乘
 */
public class RecursiveFactorial {

  public int factorial(int n) {
    if (n <= 1) {
      return 1;
    } else {
      int sub = factorial(n - 1); // 递归（贪心）
      return n * sub;
    }
  }

  public static void main(String[] args) {
    RecursiveFactorial recursiveFactorial = new RecursiveFactorial();
    System.out.println(recursiveFactorial.factorial(3));
  }
}
