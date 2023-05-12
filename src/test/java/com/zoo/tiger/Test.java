package com.zoo.tiger;

public class Test {

    /**
     * jdk8
     *
     * ➜  tiger git:(main) ✗ javap -c Test
     * 警告: 二进制文件Test包含com.zoo.tiger.Test
     * Compiled from "Test.java"
     * public class com.zoo.tiger.Test {
     *   public com.zoo.tiger.Test();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *        0: ldc           #2                  // String hello
     *        2: astore_1
     *        3: ldc           #3                  // String world
     *        5: astore_2
     *        6: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *        9: new           #5                  // class java/lang/StringBuilder
     *       12: dup
     *       13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
     *       16: aload_1
     *       17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       20: aload_2
     *       21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       27: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       30: return
     * }
     *
     *
     * jdk20
     *
     * ➜  tiger git:(main) ✗ javap -c Test
     * 警告: 文件 ./Test.class 不包含类 Test
     * Compiled from "Test.java"
     * public class com.zoo.tiger.Test {
     *   public com.zoo.tiger.Test();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *        0: ldc           #7                  // String hello
     *        2: astore_1
     *        3: ldc           #9                  // String world
     *        5: astore_2
     *        6: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *        9: aload_1
     *       10: aload_2
     *       11: invokedynamic #17,  0             // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
     *       16: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       19: return
     * }
     *
     * conclusion
     *
     * 在 JDK 8 中，字符串连接操作符（+）的实现使用了 StringBuilder，而在 JDK 11 中，字符串连接操作符（+）的实现优化为使用了 invokedynamic 指令，这可以带来更好的性能。
     *
     * 在 JDK 8 中，当使用字符串连接操作符（+）连接两个字符串时，编译器将其转换为如下代码：
     *
     * arduino
     * Copy code
     * String str1 = "Hello";
     * String str2 = "world";
     * String result = new StringBuilder(str1).append(str2).toString();
     * 在上面的代码中，字符串连接操作符（+）会创建一个 StringBuilder 对象，并使用 append() 方法将两个字符串连接起来。最后，调用 toString() 方法将 StringBuilder 对象转换为 String 对象。
     *
     * 在 JDK 11 中，字符串连接操作符（+）的实现使用 invokedynamic 指令，这可以带来更好的性能。invokedynamic 指令可以动态地解析方法调用，这意味着它可以根据运行时的类型来选择最优的实现方式。在 JDK 11 中，当使用字符串连接操作符（+）连接两个字符串时，编译器将其转换为如下代码：
     *
     * arduino
     * Copy code
     * String str1 = "Hello";
     * String str2 = "world";
     * String result = StringConcatFactory.makeConcat(str1, str2).toString();
     * 在上面的代码中，StringConcatFactory.makeConcat() 方法返回一个 StringConcatFactory 的实例，该实例根据运行时的类型来选择最优的实现方式，以达到更好的性能。
     *
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
    }


}
