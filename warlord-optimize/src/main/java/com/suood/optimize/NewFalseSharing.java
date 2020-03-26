package com.suood.optimize;// JDK 8及以上支持
//@sun.misc.Contended  //JDK8       //配合参数 -XX:-RestrictContended

//@Contended


import org.openjdk.jol.info.ClassLayout;
import sun.misc.Contended;


public class NewFalseSharing {
  @Contended("tlr")
  private volatile long usefulVal =7l;

  public static void main(String[] args) {
    NewFalseSharing sharing = new NewFalseSharing();
//    System.out.println(ClassLayout.parseClass(NewFalseSharing.class).toPrintable());
    System.out.println(ClassLayout.parseInstance(sharing).toPrintable());

//    System.out.println(ClassLayout.parseClass(ArrayList.class).toPrintable(usefulVal));
//    System.out.println(GraphLayout.parseInstance(list).toPrintable());
//    System.out.println(GraphLayout.parseInstance(list).toFootprint());
  }
}