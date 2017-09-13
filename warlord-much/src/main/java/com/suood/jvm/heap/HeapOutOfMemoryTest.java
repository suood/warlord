package com.suood.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 2017/9/13.
 */

// VM args -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
public class HeapOutOfMemoryTest {

    public void heapOOMMethod(){
        List objList = new ArrayList<OomObject>();
        while(true){
            objList.add(new OomObject());
        }

    }

    public static void main(String[] args) {
        HeapOutOfMemoryTest test = new HeapOutOfMemoryTest();
        test.heapOOMMethod();
    }

}
class  OomObject{
    public OomObject(){

    }
}