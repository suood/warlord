package com.suood.assembly;
//输出汇编用于学习JMM模型
//-Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
//查看java字节码    javap -v AssemblyAdd.class(带输出附加信息)                 javap -c AssemblyAdd.class （对代码进行反汇编）
/**

 +PrintAssembly print assembly code for bytecoded and native methods
 +PrintNMethods print nmethods as they are generated
 +PrintNativeNMethods print native method wrappers as they are generated
 +PrintSignatureHandlers print native method signature handlers
 +PrintAdapterHandlers print adapters (i2c, c2i) as they are generated
 +PrintStubCode print stubs: deopt, uncommon trap, exception, safepoint, runtime support
 +PrintInterpreter print interpreter code

 */