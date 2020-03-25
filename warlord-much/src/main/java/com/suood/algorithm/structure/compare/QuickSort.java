package com.suood.algorithm.structure.compare;

import java.util.Queue;

/**
 * <a href="https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F" >快速排序</a>
 * 又称划分交换排序（partition-exchange sort 基本步骤 1挑选基准值：从数列中挑出一个元素，称为“基准”（pivot），
 * 2分割：重新排序数列，所有比基准值小的元素摆放在基准前面， 所有比基准值大的元素摆在基准后面（与基准值相等的数可以到任何一边）。 在这个分割结束之后，对基准值的排序就已经完成，
 * 3递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。
 */
public class QuickSort extends AbstractSort {

  public QuickSort(int num) {
    super(num);
  }

  @Override
  void doSort() {
    quickSort(0,intArray.length-1);
  }

  private void quickSort(int head, int tail) {
    if (head >= tail || intArray == null || intArray.length <= 1) {
      return;
    }
    int i = head, j = tail, pivot = intArray[(head + tail) / 2];
    System.out.println(pivot);
    while (i <= j) {
      while (intArray[i] < pivot) {
        ++i;//比中位数小，前进跳过
      }
      while (intArray[j] > pivot) {
        --j;//比中位数大向后退，跳过
      }
      if (i < j) {
        int temp = intArray[i];
        intArray[i] = intArray[j];
        intArray[j] = temp;
        ++i;// MARK 交换之后 分别前移后移
        --j;
        printOrigin();
      } else if (i == j) {
        ++i;
      }
    }
    quickSort(head, j);
    quickSort(i, tail);
  }

  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort(15);
    quickSort.printOrigin();
    quickSort.doSort();
    quickSort.printOrigin();
  }
}
