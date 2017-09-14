package com.suood.jvm.heap;

import java.util.ArrayList;
import java.util.List;


//VM 1.7&Before args  -XX:PermSize=1M -XX:MaxPermSize=1M
//VM 1.8&after args  -XX:MetaspaceSize=1M -XX:MaxMetaspaceSize=1M
public class MethodAreaOOMTest {

    public static void main(String[] args) {
        List <String> stringList = new ArrayList<String>(50);
        int i = 0;
        while (true){
            stringList.add(String.valueOf(i++).intern());
        }
    }
}
