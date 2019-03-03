package com.suood.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 代理需实现接口 ,然后代理工厂将相应的对象创建后传递给相关成员变量
 */
public class JDKProxy {

    //维护一个目标对象
    private Object target;
    public JDKProxy(Object target){
        this.target=target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开始事务2");

                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("提交事务2");
                    return returnValue;
                }
        );
    }
    public void doBefor(){

    }
    public void doAfter(){

    }
    public static void main(String[] args) {
        MakeThingsDo makeThingsDo = new ThingsDo();
        JDKProxy jdkProxy = new JDKProxy(makeThingsDo);
        jdkProxy.getProxyInstance();

    }
}
