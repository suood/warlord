package com.suood.assembly;

public class NoVisibility  extends Thread{
 private  static boolean flag = false;
 private static int i =0;
// private volatile  static boolean flag = false;     // MARK volatile同理，多了ACC_VOLATILE标签，运行时处理
                    
 @Override
 public void run() {
  while (!flag) {
   i++;
  }
 }

 public static void main(String[] args) throws Exception {
  new NoVisibility().start();
  Thread.sleep(2000);
  flag = true;
  System.out.println(i);
 }
}

/**    not volatile       //如果是debug 模式 在 i++处加入断点，程序也会正常退出。^_^~!!!
 public void run();
 descriptor: ()V
 flags: ACC_PUBLIC
 Code:
 stack=1, locals=1, args_size=1
 0: getstatic     #2                  // Field flag:Z
 3: ifne          9
 6: goto          0
 9: return
 LineNumberTable:
 line 9: 0
 line 10: 9
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      10     0  this   Lcom/suood/assembly/NoVisibility;
 StackMapTable: number_of_entries = 2
// frame_type = 0 /* same
//  frame_type = 8 /* same 

 */

/**        volatile
 public void run();
 descriptor: ()V
 flags: ACC_PUBLIC
 Code:
 stack=1, locals=1, args_size=1
 0: getstatic     #2                  // Field flag:Z
 3: ifne          9
 6: goto          0
 9: return
 LineNumberTable:
 line 9: 0
 line 10: 9
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      10     0  this   Lcom/suood/assembly/NoVisibility;
 StackMapTable: number_of_entries = 2
 frame_type = 0 /* same
        frame_type = 8 /* same *

 */