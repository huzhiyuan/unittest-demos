package com.hzy.unittest.demos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

public class MockitoTesTest {
    @Mock
    DoCallRealMethodDemos mockMain;

    @Before
    public void init() {
        //这句话自动将依赖的类注入待测类，
        // 如果依赖类在spring的管理下有自己的name，那么甚至在待测类中都不需要写setter方法
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFun() {
        // 执行mock，而不是真正部分，所以没有打印任何信息
        mockMain.fun("mock test One");

        // doCallRealMethod声明后，执行真正部分
        // 但是Mock只能对public（fun1）和protected函数进行mock
        // 对private函数（fun2）仍执行真正部分
        // 所以输出fun和fun2
        //这里是说,将fun()方法的调用改成真实调用,
        doCallRealMethod().when(mockMain).fun(anyString());
        doCallRealMethod().when(mockMain).fun1(anyString());
        mockMain.fun("mock test Two");
        mockMain.fun("mock test Two2");
        //这是不是要谨慎使用? 如果私有方法不被mock的话, 一旦使用doCallRealMethod, 就可能触发真实运行

        // 执行mock，输出int的默认值0，而不是5
        System.out.println("val: " + mockMain.getVal());
        // when声明后，既不走真正部分，也不走mock，直接返回thenReturn()中定义的值
        // 注意：该值的类型需要和when中函数返回值类型一致
        when(mockMain.getVal()).thenReturn(10);
        System.out.println("val: " + mockMain.getVal());
    }
}
