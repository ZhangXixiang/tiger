package com.zoo.tiger.me.test;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tiger
 */
public class ConMapTest {
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash

    public static void main(String[] args) {
        int h = "1".hashCode();
        System.out.println(HASH_BITS);
        System.out.println("hashCode=" + ((h ^ (h >>> 16)) & HASH_BITS));


        ConcurrentHashMap map = new ConcurrentHashMap<String, String>();
        // put返回旧值，如果没有则返回null
        Object a = map.putIfAbsent("1", "1");
        System.out.println("pia=" + a);
        Object a2 = map.putIfAbsent("1", "2");
        System.out.println("pia=" + a2);
        System.out.println(Arrays.asList(map));
        map.put("1", "1");
        map.put("1", "2");
        System.out.println(Arrays.asList(map));
        Object o = map.computeIfAbsent("1", value -> 280);
        System.out.println("cia=" + o);
        // compute（相当于put,只不过返回的是新值）当key不存在时，执行value计算方法，计算value
        Object o1 = map.computeIfAbsent("2", value -> 280);
        System.out.println("cia=" + o1);
        System.out.println(Arrays.asList(map));
        Object compute = map.compute("1", (k, v) -> "x");
        System.out.println("compute=" + compute);
        System.out.println(Arrays.asList(map));

    }

}
