package com.suood.patterns.proxy;

/**
 *  静态代理，常见于Dao imp Service 这种方式的应用。
 */
public class StaticProxy  {


    public StaticProxy(MakeThingsDo makeThingsDo) {
        this.makeThingsDo = makeThingsDo;
    }

    private MakeThingsDo makeThingsDo;

    public void doSomeThing(){
        makeThingsDo.thingsMove();
    }

    public static void main(String[] args) {
        MakeThingsDo makeThingsDo = new ThingsDo();
        StaticProxy proxy = new StaticProxy(makeThingsDo);
        proxy.doSomeThing();
    }
}
class ThingsDo implements MakeThingsDo {
    public String test = "rest";
    @Override
    public void thingsMove() {
        System.out.println("impl MakeThingsDo");
    }
}
