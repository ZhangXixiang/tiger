package com.zoo.tiger.me.test;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Tiger
 */
public class CollectionTest {

    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        map.put("1","1");
        map.put("3","3");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for(Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + "=" +entry.getValue());
        }

        Set<String> strings = map.keySet();
        for(String key : strings) {
            System.out.println(key + "=" +map.get(key));
        }

        String str = "null";
        switch (str) {
            case "1":
                System.out.println("1");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }


        String a = "1";
        String b = "2";
        Assert.assertEquals(a,b);



    }

}
