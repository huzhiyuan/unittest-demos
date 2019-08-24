package com.hzy.design.doHandler;

import com.hzy.design.doHandler.interceptor.HandlerInterceptor;

public class Main {
    public static void main(String[] args) {
        new HandlerInterceptor().deal(1);
        new HandlerInterceptor().deal(2);
        new HandlerInterceptor().deal(3);
    }
}
