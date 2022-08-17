package com.example.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test {
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        fun3("a{,b{,c}}");
    }

    public static void fun1(String str){
        if (str == null || str.length() == 0) {
            System.out.println(0);
            return;
        }
        int n = 0;
        int max = 0;
        char cur = str.charAt(0);
        if (cur - '0' <= 9){
            n = 1;
            max = 1;
        }
        for (int i = 1; i < str.length(); i++){
            char c = str.charAt(i);
            if (c - '0' > 9){
                n = 0;
                i++;
                if (i < str.length()){
                    cur = str.charAt(i);
                    if (cur - '0' < 9){
                        n = 1;
                        max = Math.max(max,n);
                    }
                    continue;
                }else {
                    break;
                }

            }
            if (cur - c > 0){
                n = 0;
            }
            cur = c;
            n++;
            max = Math.max(max, n);
        }
        System.out.println(max);
    }

    public static void fun2(int[] ints){
        int length = ints.length;
        //保存有多少种数
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int a : ints) {
            int tem;
            if (map.containsKey(a)) {
                tem = map.get(a) + 1;
            } else {
                stack.push(a);
                tem = 1;
            }
            map.put(a, tem);
            max = Math.max(max, tem);
        }
        int b = stack.size();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < b; i++){
            int a = stack.pop();
            if (map.get(a) == max){
                list.add(a);
            }
        }
        int c = list.size();
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            nums[i] = list.get(i);
        }
        Arrays.sort(nums);
        if (c % 2 == 0){
            System.out.println((nums[c / 2] + nums[c/2 - 1]) / 2);
        }else {
            System.out.println(nums[c / 2]);
        }
    }



    public static void fun3(String string){
        if (string == null || string.length() == 0){
            System.out.println("");
            return;
        }
        if (string.length() == 1){
            System.out.println(string.charAt(0));
            return;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++){
            char a = string.charAt(i);
            //遇到{，则说明无左子树，需要直接弹出根节点
            //遇到单词，则说明左子树无子节点，此时弹出左子树和根节点
            //遇到}跳过
            //
            if (fun4(a)){
                char b = string.charAt(++i);
                if (b == '{'){
                    stack.push(a);
                }else if (b == ','){
                    stringBuilder.append(a);
                    stringBuilder.append(stack.pop());
                }else if (b == '}'){
                    stringBuilder.append(a);
                }
            }else if (a == ','){
                stringBuilder.append(stack.pop());
            }else {
                continue;
            }
        }
        while (stack.size() != 0){
            stringBuilder.append(stack.pop());
        }
        System.out.println(stringBuilder.toString());
    }

    public static boolean fun4(char c){
        return c != '{' && c != '}' && c != ',';
    }



}
