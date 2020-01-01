package com.suood.assembly.jmm.keyword.sync;

import com.suood.patterns.singleton.UnsafeInstance;

public class MonitorTestClass {

  public void  doJob(){
    
  }
//  public void monitorEnter(){    
//    UnsafeInstance.getUnSafe().monitorEnter();
//  }
//  public void  moniorExit(){
//    UnsafeInstance.getUnSafe().monitorExit();
//  }
  public static void main(String[] args) {
    SelfClassMonitor selfClassMonitor  =new SelfClassMonitor();
    MonitorTestClass monitorTestClass = new MonitorTestClass();
    selfClassMonitor.apply(monitorTestClass);
  }
}
