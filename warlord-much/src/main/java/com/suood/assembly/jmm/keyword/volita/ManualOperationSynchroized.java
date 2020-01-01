package com.suood.assembly.jmm.keyword.volita;

import com.suood.patterns.singleton.UnsafeInstance;

/**
 * java 通过unsafe 类 实现 几个方法同步。     手动为代码加入同步监视器
 *  // MARK 这种方法将会在后续版本中移除不再建议使用了。
 */
public class ManualOperationSynchroized {
  private Object lockObj = new Object();
  public void methodA(){
    
  }
  public void methodB(){

  }
  public void lock(){
    UnsafeInstance.getUnSafe().monitorEnter(lockObj);
  }
  public void unlock(){
    UnsafeInstance.getUnSafe().monitorExit(lockObj);
    // MARK if exception  unlock in   finally block
  }

  
}
