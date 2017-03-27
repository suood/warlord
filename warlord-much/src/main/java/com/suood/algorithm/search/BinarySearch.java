package com.suood.algorithm.search;

/**
 * Created by FENGCUIJIE on 2017/3/23.
 * 二分查找法 {1,3,4,5,6,7,8,9,12,13,47,54,56,57,99,156,568,779,897,1260,1265,35}
 * 当比目标值小的时候 高位记录-1 当比目标值大的时候 低位下标+1
 */
public class BinarySearch {



    public int search(int []array ,int gola){
        int hight= array.length -1;
        int low = 0;
        int middle = 0;
        do{
            middle =  (hight-low)/2;
            System.out.println(middle);
            if(array[middle]==gola){
                    return middle;
            }else if(array[middle]<gola){
                low ++;
                continue;
            }else {
                hight-- ;
                continue;
            }
        } while (array[middle]!=gola);
        return middle;

    }

    public static void main(String[] args) {
        int []  array = {1,3,4,5,6,7,8,9,12,13,47,54,56,57,99,156,568,779,897,1260,1265,35};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(array,9));
    }
}
