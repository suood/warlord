package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor{
    private Object target;
    public Object getProxyObjectInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);//TODO TO UNDERSTOOD
       return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object returnValue = method.invoke(target, objects);

        return returnValue;
    }

    public static void main(String[] args) {
        DoCglib doCglib = new DoCglib();
        CglibProxy proxy = new CglibProxy();
    }
}

class  DoCglib {
    public void  move(){
        System.out.println("things move");
    }
}