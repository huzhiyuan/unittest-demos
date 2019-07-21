package com.hzy.hashmap;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer,Integer> x = new HashMap<>();
        x.put(1,2);
        x.put(1,3);
        System.out.println(x);
    }
}
