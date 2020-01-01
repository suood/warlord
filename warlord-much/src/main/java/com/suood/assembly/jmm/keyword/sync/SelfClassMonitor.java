package com.suood.assembly.jmm.keyword.sync;

import com.suood.patterns.singleton.UnsafeInstance;
import java.util.function.Function;

// MARK synchronized ->自旋，轻量级锁->重量级锁；waitset mutex lock (系统级标量)
public class SelfClassMonitor  implements Function<MonitorTestClass,Object> {
  //MARK 
  @Override
  public Object apply(MonitorTestClass monitorTestClassObject) {
    UnsafeInstance.getUnSafe().monitorEnter(monitorTestClassObject.getClass());     // MARK --> lock 
    monitorTestClassObject.doJob();
    UnsafeInstance.getUnSafe().monitorExit(monitorTestClassObject.getClass());     // MARK --> unlock
    return null;
  }
  /**
   * accquire -> block -> waitset -> monitor exit ->notify -> accquire-> monitor enter
   */

}
