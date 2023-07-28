package com.zoo.tiger.me.test.pro;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class ProxyExample {
//    Lombok 的实现和反射没有任何关系，前面我们说了反射是程序在运行期的一种自省（introspect）能力，而 Lombok 的实现是在编译期就完成了，为什么这么说呢？
//    当面试官问动态代理的时候，经常会问到它和静态代理的区别？静态代理其实就是事先写好代理类，可以手工编写也可以使用工具生成，但它的缺点是每个业务类都要对应一个代理类，特别不灵活也不方便，于是就有了动态代理。
//    动态代理的常见使用场景有 RPC 框架的封装、AOP（面向切面编程）的实现、JDBC 的连接等。
//    Spring 框架中同时使用了两种动态代理 JDK Proxy 和 CGLib，当 Bean 实现了接口时，Spring 就会使用 JDK Proxy，在没有实现接口时就会使用 CGLib，我们也可以在配置中指定强制使用 CGLib，只需要在 Spring 配置中添加 <aop:aspectj-autoproxy proxy-target-class="true"/> 即可。
//    小结
//    本课时我们介绍了 JDK Proxy 和 CGLib 的区别，JDK Proxy 是 Java 语言内置的动态代理，必须要通过实现接口的方式来代理相关的类，而 CGLib 是第三方提供的基于 ASM 的高效动态代理类，它通过实现被代理类的子类来实现动态代理的功能，因此被代理的类不能使用 final 修饰。
    static interface Car {
        //
//    static class Car {
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
