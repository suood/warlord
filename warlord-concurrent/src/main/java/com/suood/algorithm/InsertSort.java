package com.suood.algorithm;

/**
 * Created by FENGCUIJIE on 2017/3/17.
 * 数组a[n]插入排序认为起始元素a[0]组为有序组，后面的数组元素为无序组a[1]~a[n] 我们认为有序组为前面的j个元素，无序组为自i开始的后序元素
 * 那么下标i 与j 的关系为，j= i-1 i至少要大于1 排序才有意义（j下标仅仅用于理解有序组和无序组）在排序过程中并无实际意义。
 * 当排序开始时 我们每次都从有序组的第一个元素(下标为0)跟 跟i进行对比 直到找到与排序规则不符的元素或者循环到有序组的末尾，
 * 当找到不符合排序规则的有序组元素 下表为t。
 * 此时应当标记当前无序组第一个元素 i ,tem = a[i] (0<=t<i) 按照排序条件比有序组第t个元素更应该放在t的位置
 * 也就是说 整个数组的t~j后移一位 i位置 与 t位置互换 应有 i - t 个元素要移动 应有 k =i ;k>t;k--
 * 然后有序组继续从0开始遍历 i标记后移一位
 * 算法最大运行次数为 1+2+3+4+5+6+7+8~~+n 即为 n*（n+1)/2
 * (n^2+n)/2 有 θ（n^2）
 * 算法复杂度为 O（n^2）
 */
public class InsertSort {


    
    public int[] insertSort(int [] inputArray){
        for (int i =1; i < inputArray.length; i++) {
            int t =0;

            while(inputArray[i]>inputArray[t] && (t<i) ){
                t++;
                //j不断地增大。 直到出现 不满足while条件的时候 也就是说
                //j<i 或者出现 inputArray[i]<inputArray[j]
                if (i<t)
                    System.out.println(i+" "+t);
            }
            if (i!=t){
                int swp = inputArray[i];
                for (int k=i;k>t;k--){
                    inputArray[k]=inputArray[k-1];
                }
                 inputArray[t]=swp;
            }
        }
        return  inputArray;
    }

    public static void main(String[] args) {
        int [] array = {22, 55, 97, 87 ,94 ,75, 44, 6 ,57 ,12 ,81 ,28 ,208 ,99 ,72 ,21 ,10 ,17 ,67 ,14};
        InsertSort sortObj = new InsertSort();
        sortObj.insertSort(array);
        for (int i : array) {
            System.out.print(i);
            System.out.print(",");
        }

    }
}
