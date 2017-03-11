package com.suood.algorithm;

import java.util.Arrays;
import java.util.OptionalDouble;

/**
 * Created by FENGCUIJIE on 2017/3/11.
 */
public class Calculate {
    static  int [] array = {77 ,34 ,91 ,71 ,84 ,50 ,29 ,87 ,93 ,68};

    double average(int[]arrayOfInt){
        return  Arrays.stream(arrayOfInt).average().getAsDouble();
    }

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        System.out.println(calculate.average(Calculate.array) );
    }
}
