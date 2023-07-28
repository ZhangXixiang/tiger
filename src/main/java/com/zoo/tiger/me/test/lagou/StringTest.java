package com.zoo.tiger.me.test.lagou;

import java.util.Objects;

public class StringTest {

    //    字符串匹配
    //    子串 google
    //    主串 goodgoogle

    public static void main(String[] args) {
//        strMatch("goodgoogle", "google");
//        strMatch2("goodgoogle", "google");
        sameSonStr("111123434","1111223435");
    }

    public static void strMatch(String main, String son) {

        if (Objects.isNull(main) || Objects.isNull(son) || main.length() < son.length()) {
            System.out.println(-1);
        }

        int firstSame = 0;

        for (int i = firstSame; i < main.length(); i++) {

            for (int j = 0; j < son.length(); j++) {
                // 依次比较每一个字符
                if (main.charAt(i) == son.charAt(j)) {
                    i++;
                } else {
                    firstSame++;
                    break;
                }
            }
            if (i - firstSame == son.length() + 1) {
                System.out.println(1);
                return;
            } else {
                System.out.println(-1);
            }
            i = firstSame;

        }

    }


    public static void strMatch2(String main, String son) {
        if (Objects.isNull(main) || Objects.isNull(son) || main.length() < son.length()) {
            System.out.println(-1);
        }

        for (int i = 0; i < main.length(); i++) {
        int sucMatchTimes = 0;
            for (int j = 0; j < son.length(); j++) {
                if (main.charAt(i + j) != son.charAt(j)) {
                    break;
                }
                sucMatchTimes++;

            }

            if (sucMatchTimes == son.length()) {
                System.out.println(1);
                return;
            }
        }
    }


//    最长公共子串
    public static String sameSonStr(String a, String b) {
        String maxMatchStr = "";
        int maxMatchNum = 0;
        for (int i = 0; i < a.length(); i++) {
            int sucMatchTimes = 0;
            for (int j = 0; j < b.length(); j++) {
//                找相同的第一个字符
                if (a.charAt(i) == b.charAt(j)) {
                    for(int m = i , n = j ; m < a.length() && n < b.length(); m++,n++) {
                        if(a.charAt(m) != b.charAt(n)){
                            break;
                        }
                        if(m - i + 1>maxMatchNum){
                            maxMatchNum = m - i + 1;
                            maxMatchStr = a.substring(i,m + 1);
                        }



                    }

                }
            }
        }
        System.out.println(maxMatchStr);
        return maxMatchStr;
    }


}
