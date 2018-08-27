package com.suood.deepin;

import sun.misc.Contended;

public class SomePopularObject {

    @Contended
    public volatile long usefulVal;
    public volatile long anotherVal;

    public static void main(String[] args) {
        SomePopularObject somePopularObject = new SomePopularObject();
        System.out.println();
}
}
