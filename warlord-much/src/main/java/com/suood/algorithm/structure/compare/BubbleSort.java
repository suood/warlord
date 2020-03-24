package com.suood.algorithm.structure.compare;

/**
 * 冒泡排序算法的运作如下：
 *
 * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 3.针对所有的元素重复以上的步骤，除了最后一个。
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSort extends AbstractSort{


    public BubbleSort (int t ){
      super(t);
    }

    @Override
    public void doSort(){
      for (int i = 0; i < intArray.length-1; i++)
        for (int j = 0; j < intArray.length-i-1; j++)
          if (intArray[j] > intArray[j+1])
          {
            // swap arr[j+1] and arr[i]
            int temp = intArray[j];
            intArray[j] = intArray[j+1];
            intArray[j+1] = temp;
          }
    }


  public static void main(String[] args) {
    BubbleSort bubbleSort = new BubbleSort(10);
    bubbleSort.printOrigin();
    bubbleSort.doSort();
    bubbleSort.printOrigin();
  }
}
