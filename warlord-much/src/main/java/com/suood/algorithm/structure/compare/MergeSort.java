package com.suood.algorithm.structure.compare;

/**
 * <a href="https://zh.wikipedia.org/wiki/%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F">wiki 归并排序</a>
 * a。递归法 1申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列 2设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 3比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置 4重复步骤3直到某一指针到达序列尾 5将另一序列剩下的所有元素直接复制到合并序列尾。 b.迭代法
 * 1序列每相邻两个数字进行归并操作，形成{\displaystyle ceil(n/2)}{\displaystyle ceil(n/2)}个序列，排序后每个序列包含两/一个元素
 * 2若此时序列数不是1个则将上述序列再次归并，形成{\displaystyle ceil(n/4)}{\displaystyle ceil(n/4)}个序列，每个序列包含四/三个元素
 * 3重复步骤2，直到所有元素排序完毕，即序列数为1
 */
public class MergeSort extends AbstractSort {

  public MergeSort(int num) {
    super(num);
  }

  @Override
  void doSort() {
    int[] result = new int[intArray.length];
    merge_sort_recursive(intArray, result, 0, intArray.length - 1);
  }

  public void create() {
    int first = 1;
    int second = 1;
    int[] tempArray = new int[first + second];

  }

  // FIXME
  void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
    if (start >= end) {
      return;
    }
    // MARK 获取长度，长度除以2 右移1位即为除以2 加上起始位置，作为结束位置。
    int len = end - start, mid = (len >> 1) + start;
    int start1 = start, end1 = mid;
    int start2 = mid + 1, end2 = end;
    merge_sort_recursive(arr, result, start1, end1);
    merge_sort_recursive(arr, result, start2, end2);
    int k = start;
    while (start1 <= end1 && start2 <= end2) {
      result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
    }
    while (start1 <= end1) {
      result[k++] = arr[start1++];
    }
    while (start2 <= end2) {
      result[k++] = arr[start2++];
    }
    for (k = start; k <= end; k++) {
      arr[k] = result[k];
    }
  }
//  public static void merge_sort(int[] arr) {
//    int len = arr.length;
//    int[] result = new int[len];
//    merge_sort_recursive(arr, result, 0, len - 1);
//  }
public static void main(String[] args) {
  MergeSort mergeSort = new MergeSort(15);
  mergeSort.printOrigin();
  mergeSort.doSort();
  mergeSort.printOrigin();
}
}
