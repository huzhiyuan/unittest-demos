package com.hzy.unittest.demos;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SpyDemosTest {
    @Test
    public void callRealMethodTest() {
        SpyDemos jerry = Mockito.mock(SpyDemos.class);
        Mockito.doCallRealMethod().when(jerry).goHome();
        Mockito.doCallRealMethod().when(jerry).doSomeThingB();
        jerry.goHome();

        Mockito.verify(jerry,Mockito.times(1)).doSomeThingA();
        Mockito.verify(jerry,Mockito.times(1)).doSomeThingB();
    }
    // 当需要整体执行真正部分，只有少部分方法执行mock，选用这种方式
    @Test
    public void spyTest() {
        SpyDemos spyJack = Mockito.spy(new SpyDemos());
        // 用thenReturn 会走go()方法体，然后将返回值Mock掉
        Mockito.when(spyJack.go()).thenReturn(false);
        Assert.assertFalse(spyJack.go());
        // 用doReturn 不走go()方法体
        Mockito.doReturn(false).when(spyJack).go();
        Assert.assertFalse(spyJack.go());
    }

}
