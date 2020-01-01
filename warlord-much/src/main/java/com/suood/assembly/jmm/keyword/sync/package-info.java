package com.suood.assembly.jmm.keyword.sync;
//同步 synchronized


// 每个对象 创建之后都会有一个monitor对象 我们将它叫做监视器对象，或者叫做管程。 monitor 是可以重入的排他锁。
//自旋-偏向->公平->重量级锁。
// 在系统级别，重量级锁 mutex lock ->互斥变量 