package com.hzy.unittest.demos;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BaseTest {

    @Test
    public void verifyTest(){
        List mockedList = mock(List.class);
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("one");
        //mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
        verify(mockedList,times(1)).add("one");
    }

    @Test
    public void stubTest(){
        // 你可以mock具体的类,不止是接口
        LinkedList mockedList = mock(LinkedList.class);
        //打桩
        when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(999));
        when(mockedList.get(999)).thenReturn("999");
        System.out.println(mockedList.get(999));

    }
}
