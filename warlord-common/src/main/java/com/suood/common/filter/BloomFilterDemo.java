package com.suood.common.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 2019-12-06
 */
public class BloomFilterDemo {

  BloomFilter bloomFilter = BloomFilter.create(Funnels.byteArrayFunnel(), 100);
}
