package com.suood.algorithm;
import com.google.common.base.Joiner;

/**
 * Created by Alexander on 2017/2/27.
 */
public class BubbleSort  {

    public   void sort(Integer[] a) {
        int temp = 0;
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < len - i; j++)
                if (a[j - 1] > a[j]) {
                    //注意分清是a[j-1]还是a[j]不然容易出现边界问题
                    // 从小到大排序
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
        }
    }

    public static void main(String[] args) {
        Integer []array = {9,54,568,1,56,3,5,7,8,6,897,4,13,12,57,156,779,1265,1260,47,99};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);
        System.out.println(Joiner.on(",").join(array));
    }
}
