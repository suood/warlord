package com.suood.algorithm.structure.compare;

/**
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。 插入排序在实现上，通常采用in-place排序（即只需用到{\displaystyle
 * O(1)}{\displaystyle O(1)}的额外空间的排序）， 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 */
public class InsertionSort extends AbstractSort {

  public InsertionSort(int size) {
    super(size);
  }

  @Override
  void doSort() {
    for (int i = 1; i < intArray.length - 1; i++) {
     int current = intArray[i];
     int j = i-1;
     while (intArray[j]>intArray[i] && (j>0)){
        swap(j,i);
        j--;
     }
    }
  }

  /**
   * 交换
   *
   * @param from
   * @param to
   */
  public void swap(int from, int to) {
    int temp = intArray[to];
    intArray[to] = intArray[from];
    intArray[from] = temp;
  }

  public static void main(String[] args) {
    InsertionSort insertionSort = new InsertionSort(15);
    insertionSort.printOrigin();
    insertionSort.doSort();
    insertionSort.printOrigin();
  }
}
