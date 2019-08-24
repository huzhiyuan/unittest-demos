package com.hzy.hashmap;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapThreadSafeTest {
    public static HashMap<String, String> hashMap = new HashMap<>();

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        Thread t1 = new Thread(new OperateHashMap());
        Thread t2 = new Thread(new OperateHashMap2());

        t1.start();
        t2.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println(hashMap.get(String.valueOf(i)));
        }
    }
}

class OperateHashMap implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            HashMapThreadSafeTest.hashMap.put(String.valueOf(i), String.valueOf(i));
        }
    }
}

class OperateHashMap2 implements Runnable {
    @Override
    public void run() {
        for (int i = 25; i < 50; i++) {
            HashMapThreadSafeTest.hashMap.put(String.valueOf(i), String.valueOf(i));
        }
    }
}

