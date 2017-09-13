package com.suood.algorithm;

import com.google.common.math.DoubleMath;
import com.google.common.math.Stats;
import org.apache.commons.math.stat.descriptive.moment.Mean;

import java.util.Arrays;

/**
 * Created by Alexander on 2017/3/11.
 */
public class Calculate {
    static  int [] array = {77 ,34 ,91 ,71 ,84 ,50 ,29 ,87 ,93 ,68};

    double averageJDK8(int[]arrayOfInt){
        return  Arrays.stream(arrayOfInt).average().getAsDouble();
    }
    double averageGuava(int[]arrayOfInt){
        return Stats.meanOf(array);
    }
//    double averageCommons(int[]arrayOfInt){
//        Mean mean = new Mean();
//        return mean.setData((double[]) array);
//    }
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        System.out.println(calculate.averageJDK8(Calculate.array) );
        System.out.println(calculate.averageGuava(Calculate.array) );
    }
}
