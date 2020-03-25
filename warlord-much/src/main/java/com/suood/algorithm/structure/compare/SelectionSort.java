package com.suood.algorithm.structure.compare;

/**
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * <p>
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。 选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上， 因此对{\displaystyle
 * n}n个元素的表进行排序总共进行至多{\displaystyle n-1}{\displaystyle n-1}次交换。 在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 */
public class SelectionSort extends AbstractSort {

  public SelectionSort(int t) {
    super(t);
  }

  @Override
  void doSort() {
    for (int i = 0; i < intArray.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < intArray.length; j++) {
        if (intArray[min] > intArray[j]) {
          min = j;
        }
      }
      if (min != i) {
        int temp = intArray[i];
        intArray[i] = intArray[min];
        intArray[min] = temp;
      }
    }
  }

  public static void main(String[] args) {

    SelectionSort selectionSort = new SelectionSort(150);
    selectionSort.printOrigin();
    selectionSort.doSort();
    selectionSort.printOrigin();
  }
}
