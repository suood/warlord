package com.suood.jdkdemo;

/**
 * Created by Alexander on 2017/3/18.
 * 代码执行顺序是 父类静态块，子类静态块，创建对象时
 * 首先执行父类非静态块，
 * 然后执行父类构造方法
 * 然后执行子类非静态语句块
 * 最后执行子类构造方法
 *
 * 一旦父类有明确的构造方法 而且带有参数 那么就要进行 super()显式调用
 */
public class Son extends Father{
    {
        System.out.println("son");
    }
    static {
        System.out.println("static son block");
    }
    public Son(){
        super("");
        this.hashCode();
        System.out.println("call Sun");
    }

    public static void main(String[] args) {
//
        System.out.println("--------");
        new Son();
    }
}

class  Father{
    {
        System.out.println("father");
    }
    static {
        System.out.println("static father block");
    }
//    public Father(){
//        System.out.println("call Father");
//    }
    public Father(String name){
        System.out.println("call Father name");
    }

}