package com.zoo.tiger.me.test.pro;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class ProxyExample {

    static interface Car {
        void running();
    }

    static class Bus implements Car{
        @Override
        public void running(){
            System.out.println("bus run");
        }
    }

    static class Trank implements Car{
        @Override
        public void running() {
            System.out.println("trank run");
        }
    }

    static class JdkProxy implements InvocationHandler{

        private Object target;


        public Object getInstance(Object target){
            this.target = target;
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("方法调用前业务处理,这里可以实现一些自己的逻辑，这就是动态代理的作用");
            return method.invoke(target, args);
        }
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Car ins = (Car) jdkProxy.getInstance(new Bus());
        ins.running();
    }
}
