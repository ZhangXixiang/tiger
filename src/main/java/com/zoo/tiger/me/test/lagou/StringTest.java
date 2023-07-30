package com.zoo.tiger.me.test.lagou;

import java.util.Objects;
import java.util.Stack;

public class StringTest {

    //    字符串匹配
    //    子串 google
    //    主串 goodgoogle

    public static void main(String[] args) {
//        strMatch("goodgoogle", "google");
//        strMatch2("goodgoogle", "google");
        System.out.println(sameSonStr("111123434","1111223435"));
        System.out.println("--------");
        System.out.println(sameSonStr1("111123434","1111223435"));
        System.out.println("--------");
        reverseSentence("this is a dog");
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

    // 最长公共子串
    public static String sameSonStr1(String a, String b) {

        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        int[][] state = new int[b.length() + 1][a.length() + 1];
        //动态转换方程 a[i][j] = a[i-1]+a[j-1]+1
        for(int i = 1 ; i <= bArray.length ; i++){
            for(int j = 1 ; j <= aArray.length ; j++){
                if(bArray[i-1] == aArray[j-1]){
                    state[i][j] = state[i-1][j-1]+1;
                }
            }
        }

        int maxNum = 0 ;
        int index = 0;
        for(int i = 0 ; i < bArray.length ; i++){
            for(int j = 0 ; j < aArray.length ; j++) {
                if(state[i][j] > maxNum){
                    maxNum = state[i][j];
                    index = i;
                }
            }
        }

        return a.substring(index-maxNum,maxNum);
    }

    // 字符串反转 this is a dog ---> dog a is this
    public static void reverseSentence(String str){
        Stack<String> stack = new Stack<>();
        String temp = "";
        for(int i = 0 ; i < str.length() ; i++){
            if(str.charAt(i) !=' '){
                temp+=str.charAt(i);
            } else if(temp != "") {
                stack.push(temp);
                temp = "";
            }
                else {continue;}
        }

        if(temp != ""){
            stack.push(temp);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
            System.out.println("空格");
        }


    }


}
