package com.suood.assembly.jmm.keyword.volita;

public class MemoryBarrierTest {

    private int a, b;
    private volatile int c, d;

    private void test() {
        int i, j;
        i = a; // load a
        j = b; // load b
        i = c; // load c
        // LoadLoad
        j = d; // load d
        // LoadStore
        a = i; // store a
        b = j; // store b
        // StoreStore
        c = i; // store c
        // StoreStore
        d = j; // store d
        // StoreLoad
        i = d; // load d
        // LoadLoad
        // LoadStore
        j = b; // load b
        a = i; // store a
    }

}
