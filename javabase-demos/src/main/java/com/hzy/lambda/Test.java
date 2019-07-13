package com.hzy.lambda;

public class Test {
    public static void main(String[] args) {
        String test = "dsferDFGREwefwsdBRTHRBHD";
        boolean hasUpperCase = test.chars().anyMatch((int ch) -> Character.isUpperCase(ch));
        System.out.println(hasUpperCase);
    }
}
