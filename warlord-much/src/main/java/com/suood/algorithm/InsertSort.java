package com.suood.algorithm;

/**
 * Created by Alexander on 2017/3/17.
 * 数组a[n]插入排序认为起始元素a[0]组为有序组，后面的数组元素为无序组a[1]~a[n].
 * 假设数组的有序组为前面的j个元素，无序组为自i开始的后序元素
 * 那么下标i 与j 的关系为，j= i-1 i至少要大于1 排序才有意义（j下标仅仅用于理解有序组和无序组）在排序过程中并无实际意义。
 * 当排序开始时,每次都从有序组的第一个元素(下标为0)跟 跟i进行对比 直到找到与排序规则不符的元素或者循环到有序组的末尾，
 * 当找到不符合排序规则的有序组元素 下表为t。
 * 此时应当标记当前无序组第一个元素 i ,temp = a[i] (0<=t<i) 按照排序条件比有序组第t个元素更应该放在t的位置
 * 即整个数组的下标t至j的元素后移一位,i位置元素换位到下标t位置，有 i-t+1 个元素要移动。
 * 所以我们移动数组元素
 * for(k =i ;k>t;k--){a[k]=a[k-1]}
 * a[t] =temp;
 * 然后有序组继续从0开始遍历且 i标记后移一位
 * 由于 每次移动i-t+1个元素 且i<n 。
 * 算法最大运行次数为 1+2+3+4+5+6+7+8+...+（n-1） 即为 n*（n+1)/2  为算数级数
 * (n^2+n)/2 有 θ（n^2）
 * 算法复杂度为 O(n^2)
 * 附算数级数求和
 * 令a=1+2+……+n
 * 由加法交换律
 * a=n+……+2+1
 * 相加
 * a+a=(1+n)+(2+n-1)+……+(n-1+2)+(n+1)
 * 2a=(n+1)+(n+1)+……+(n+1) 共有n个（n+1）
 * 所以2a=n(n+1)
 * 所以1+2+……+n=n(n+1)/2
 */
public class InsertSort {


    
    public int[] insertSort(int [] inputArray){
        for (int i =1; i < inputArray.length; i++) {
            int t =0;

            while(inputArray[i]>inputArray[t] && (t<i) ){
                t++;
                //t不断地增大。 直到出现 不满足while条件的时候 也就是说
                //t=i 或者出现 inputArray[i]<inputArray[t]
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
