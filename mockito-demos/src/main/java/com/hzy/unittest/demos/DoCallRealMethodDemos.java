package com.hzy.unittest.demos;

public class DoCallRealMethodDemos {

    public void fun(String s) {
        System.out.println(s + " : fun");
        fun1(s);
        fun2(s);
    }

    public void fun1(String s) {
        System.out.println(s + " : fun1");
    }

    private void fun2(String s) {
        System.out.println(s + " : fun2");
    }

    public int getVal() {
        return 5;
    }

}
