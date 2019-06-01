package com.hzy.unittest.demos;

public class SpyDemos {
    public void goHome() {
        doSomeThingA();
        doSomeThingB();
        doSomePrivateThingA();
    }
    public void doSomeThingB() {
        System.out.println("good day");
    }

    public void doSomeThingA() {
        System.out.println("you should not see this message.");
        doSomeThingB();
    }
    public boolean go() {
        System.out.println("I say go go go!!");
        return true;
    }

    private void doSomePrivateThingA() {
        System.out.println("private A");
    }
}
