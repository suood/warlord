package com.suood.algorithm;

/**
 * Created by FENGCUIJIE on 2017/3/11.
 * 生成等比数列
 */
public class GeometricProgression {

    public double Generator(int num ,int step){
        double value =0;
        for (int i=1;i<=num;i++){
               value += (1/StrictMath.pow(step,i-1));
        }
        return value;
    }

    public static void main(String[] args) {
        GeometricProgression recurrence = new GeometricProgression();
        System.out.println(recurrence.Generator(10,4));
    }
}
