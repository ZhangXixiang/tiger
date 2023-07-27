package com.zoo.tiger;

import com.zoo.tiger.me.model.People;

import java.util.*;

public class Test {

    /**
     * jdk8
     * <p>
     * ➜  tiger git:(main) ✗ javap -c Test
     * 警告: 二进制文件Test包含com.zoo.tiger.Test
     * Compiled from "Test.java"
     * public class com.zoo.tiger.Test {
     * public com.zoo.tiger.Test();
     * Code:
     * 0: aload_0
     * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     * 4: return
     * <p>
     * public static void main(java.lang.String[]);
     * Code:
     * 0: ldc           #2                  // String hello
     * 2: astore_1
     * 3: ldc           #3                  // String world
     * 5: astore_2
     * 6: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
     * 9: new           #5                  // class java/lang/StringBuilder
     * 12: dup
     * 13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
     * 16: aload_1
     * 17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 20: aload_2
     * 21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     * 27: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     * 30: return
     * }
     * <p>
     * <p>
     * jdk20
     * <p>
     * ➜  tiger git:(main) ✗ javap -c Test
     * 警告: 文件 ./Test.class 不包含类 Test
     * Compiled from "Test.java"
     * public class com.zoo.tiger.Test {
     * public com.zoo.tiger.Test();
     * Code:
     * 0: aload_0
     * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     * 4: return
     * <p>
     * public static void main(java.lang.String[]);
     * Code:
     * 0: ldc           #7                  // String hello
     * 2: astore_1
     * 3: ldc           #9                  // String world
     * 5: astore_2
     * 6: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
     * 9: aload_1
     * 10: aload_2
     * 11: invokedynamic #17,  0             // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
     * 16: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     * 19: return
     * }
     * <p>
     * conclusion
     * <p>
     * 在 JDK 8 中，字符串连接操作符（+）的实现使用了 StringBuilder，而在 JDK 11 中，字符串连接操作符（+）的实现优化为使用了 invokedynamic 指令，这可以带来更好的性能。
     * <p>
     * 在 JDK 8 中，当使用字符串连接操作符（+）连接两个字符串时，编译器将其转换为如下代码：
     * <p>
     * arduino
     * Copy code
     * String str1 = "Hello";
     * String str2 = "world";
     * String result = new StringBuilder(str1).append(str2).toString();
     * 在上面的代码中，字符串连接操作符（+）会创建一个 StringBuilder 对象，并使用 append() 方法将两个字符串连接起来。最后，调用 toString() 方法将 StringBuilder 对象转换为 String 对象。
     * <p>
     * 在 JDK 11 中，字符串连接操作符（+）的实现使用 invokedynamic 指令，这可以带来更好的性能。invokedynamic 指令可以动态地解析方法调用，这意味着它可以根据运行时的类型来选择最优的实现方式。在 JDK 11 中，当使用字符串连接操作符（+）连接两个字符串时，编译器将其转换为如下代码：
     * <p>
     * arduino
     * Copy code
     * String str1 = "Hello";
     * String str2 = "world";
     * String result = StringConcatFactory.makeConcat(str1, str2).toString();
     * 在上面的代码中，StringConcatFactory.makeConcat() 方法返回一个 StringConcatFactory 的实例，该实例根据运行时的类型来选择最优的实现方式，以达到更好的性能。
     * <p>
     * 因此，JDK 11 中字符串连接操作符（+）的实现比 JDK 8 更加高效。
     *
     * @param args
     */
    public static void main(String[] args) {

        String a = "hello";

        String b = "world";

        System.out.println(a + b);

        String c = "1";

        System.out.println(c.hashCode());


        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(15);
        // 初始化的时候threshold是初始容量，可以看下HashMap的源码定义threshold的时候的英文注释
        //    /**
        //     * The next size value at which to resize (capacity * load factor).
        //     *
        //     * @serial
        //     */
        //    // (The javadoc description is true upon serialization.
        //    // Additionally, if the table array has not been allocated, this
        //    // field holds the initial array capacity, or zero signifying
        //    // DEFAULT_INITIAL_CAPACITY.)
        //    int threshold;

        // new 一个hashmap的时候 内部的table是空的啊，所以put值的时候会先resize() 然后赋值真正的capacity和threshold
        objectObjectHashMap.put("a",1);

        // 示例 -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
        // 实际 -Xms2m -Xmx2m -XX:+PrintHeapAtGC  堆溢出
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        //	at java.util.Arrays.copyOf(Arrays.java:3210)
        /*List<People> list = new ArrayList<>();
        while (true) {
            list.add(new People(1,"a"));
        }*/

//        -Xss128k -XX:+HeapDumpOnOutOfMemoryError


        // 常量池溢出  VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为???这里是1.6的例子
        /*Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }*/

        // 下面这种应该是会回收掉jdk1.8 没有溢出的情况
        /*int j = 1;
        Set<String> set = new HashSet<String>();
        while (true) {
            String.valueOf(j).intern();
//            set.add(intern);
            j ++;
//            System.out.println(abc);
        }*/

        /*String s1 = new String("Java");
        String s2 = s1.intern();
        System.out.println("s1 == s2:" + s1 == s2);//false
        String s3 = "Java";
        System.out.println(s2 == s3);
        System.out.println(s2 == s3);
        // 这里按照 运算符的顺序会先计算前面的字符串相加 == s3 😭
        System.out.println("s2 == s3" + s2 == s3); // false
        System.out.println("s2 == s3" + (s2 == s3));// s2 == s3true


        String s4 = "abccc";
        String s5 = "abccc";
        String s6 = new String("abccc");
        System.out.println(s4 == s5);//true
        System.out.println(s4 == s6);//false

        String s7 = new String("Java");
        System.out.println(s1 == s7);// false*/

//        浅克隆
        People[]  o1  =  {new  People(1,  "Java")};
        People[]  o2  =  Arrays.copyOf(o1,  o1.length);
        //  修改原型对象的第一个元素的值
        o1[0].setName("Jdk");
        System.out.println("o1:"  +  o1[0].getName());
        System.out.println("o2:"  +  o2[0].getName());

    }


}
