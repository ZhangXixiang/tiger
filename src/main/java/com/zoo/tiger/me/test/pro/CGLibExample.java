package com.zoo.tiger.me.test.pro;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibExample {

    static class Car{
        public void running(){
            System.out.println("car run");
        }
    }

    static class CGLibProxy implements MethodInterceptor {

        private Object target;

        public Object getInstance(Object target){
            this.target = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(this.target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("方法调用前业务处理,这里可以实现一些自己的逻辑，这就是动态代理的作用");
            return methodProxy.invokeSuper(o,objects);
        }
    }

    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Car instance = (Car)cgLibProxy.getInstance(new Car());
        instance.running();
    }
}
