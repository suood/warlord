package com.suood.assembly;

public class IntegerVolatileAdd {
    private static volatile int c ;

    public static void main(String[] args) {
        int a,b;
        a=1 ;
        b=2;
        c = a+b;
        System.out.println(c);
    }
}
/**
 Last modified 2019-12-28; size 638 bytes
 MD5 checksum fe20ab255cf6bf688515ec95ed299fdc
 Compiled from "IntegerVolatileAdd.java"
 public class com.suood.assembly.IntegerVolatileAdd
 minor version: 0
 major version: 52
 flags: ACC_PUBLIC, ACC_SUPER
 Constant pool:
 #1 = Methodref          #6.#24         // java/lang/Object."<init>":()V
 #2 = Fieldref           #5.#25         // com/suood/assembly/IntegerVolatileAdd.c:I
 #3 = Fieldref           #26.#27        // java/lang/System.out:Ljava/io/PrintStream;
 #4 = Methodref          #28.#29        // java/io/PrintStream.println:(I)V
 #5 = Class              #30            // com/suood/assembly/IntegerVolatileAdd
 #6 = Class              #31            // java/lang/Object
 #7 = Utf8               c
 #8 = Utf8               I
 #9 = Utf8               <init>
 #10 = Utf8               ()V
 #11 = Utf8               Code
 #12 = Utf8               LineNumberTable
 #13 = Utf8               LocalVariableTable
 #14 = Utf8               this
 #15 = Utf8               Lcom/suood/assembly/IntegerVolatileAdd;
 #16 = Utf8               main
 #17 = Utf8               ([Ljava/lang/String;)V
 #18 = Utf8               args
 #19 = Utf8               [Ljava/lang/String;
 #20 = Utf8               a
 #21 = Utf8               b
 #22 = Utf8               SourceFile
 #23 = Utf8               IntegerVolatileAdd.java
 #24 = NameAndType        #9:#10         // "<init>":()V
 #25 = NameAndType        #7:#8          // c:I
 #26 = Class              #32            // java/lang/System
 #27 = NameAndType        #33:#34        // out:Ljava/io/PrintStream;
 #28 = Class              #35            // java/io/PrintStream
 #29 = NameAndType        #36:#37        // println:(I)V
 #30 = Utf8               com/suood/assembly/IntegerVolatileAdd
 #31 = Utf8               java/lang/Object
 #32 = Utf8               java/lang/System
 #33 = Utf8               out
 #34 = Utf8               Ljava/io/PrintStream;
 #35 = Utf8               java/io/PrintStream
 #36 = Utf8               println
 #37 = Utf8               (I)V
 {
 public com.suood.assembly.IntegerVolatileAdd();
 descriptor: ()V
 flags: ACC_PUBLIC
 Code:
 stack=1, locals=1, args_size=1
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return
 LineNumberTable:
 line 3: 0
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0       5     0  this   Lcom/suood/assembly/IntegerVolatileAdd;

 public static void main(java.lang.String[]);
 descriptor: ([Ljava/lang/String;)V
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=2, locals=3, args_size=1
 0: iconst_1
 1: istore_1
 2: iconst_2
 3: istore_2
 4: iload_1
 5: iload_2
 6: iadd
 7: putstatic     #2                  // Field c:I
 10: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 13: getstatic     #2                  // Field c:I
 16: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 19: return
 LineNumberTable:
 line 8: 0
 line 9: 2
 line 10: 4
 line 11: 10
 line 12: 19
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      20     0  args   [Ljava/lang/String;
 2      18     1     a   I
 4      16     2     b   I
 }
 SourceFile: "IntegerVolatileAdd.java"

 */