package com.suood.assembly.jmm.keyword.sync;

import com.suood.patterns.singleton.UnsafeInstance;
import java.util.function.Function;

public class SelfClassMonitor  implements Function<MonitorTestClass,Object> {
  //MARK 
  @Override
  public Object apply(MonitorTestClass monitorTestClassObject) {
    UnsafeInstance.getUnSafe().monitorEnter(monitorTestClassObject.getClass());
    monitorTestClassObject.doJob();
    UnsafeInstance.getUnSafe().monitorExit(monitorTestClassObject.getClass());
    return null;
  }


}
