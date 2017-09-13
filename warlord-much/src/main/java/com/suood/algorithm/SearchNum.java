package com.suood.algorithm;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexander on 2017/3/11.
 */
public class SearchNum {
    static  Integer [] array = {77 ,34 ,91 ,71 ,84 ,50 ,29 ,87 ,93 ,68};
    //查找最大值
   Integer searchMax(Integer [] array){
       int max = 0;
       for (int i = 0; i < array.length; i++) {
           if (max==0){
               max = array[i];
           }else if (max<array[i]){
               max =array[i];
           }else {
               continue;
           }
       }
       return max;
    }

    /**
     * 查找最小值
     * @param array
     * @return
     */
    Integer searchMin(Integer [] array){
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max==0){
                max = array[i];
            }else if (max>array[i]){
                max =array[i];
            }else {
                continue;
            }
        }
        return max;
    }

    Integer searchTargetMax(Integer [] array,int target  ){
        Ordering<Integer> ordering = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return right-left;
            }
        };
        List<Integer> list = Arrays.asList(array);
       return   ordering.natural().greatestOf(list,target).get(target-1);

    }

    public static void main(String[] args) {
        SearchNum searchNum = new SearchNum();
        System.out.println(searchNum.searchTargetMax(SearchNum.array,7));
        System.out.println( searchNum.searchMax(SearchNum.array));
        System.out.println( searchNum.searchMin(SearchNum.array));
    }
}
