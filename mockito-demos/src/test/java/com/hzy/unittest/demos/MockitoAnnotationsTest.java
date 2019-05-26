package com.hzy.unittest.demos;

import com.hzy.unittest.dao.AaaDao;
import com.hzy.unittest.dao.BbbDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doCallRealMethod;

public class MockitoAnnotationsTest {
    @InjectMocks
    MockitoAnnotationsDemos abcService;

    @Mock
    AaaDao aaaDao;

    @Mock
    BbbDao bbbDao;

    @Before
    public void setup(){
        //这句话执行以后，aaaDao和bbbDao自动注入到abcService中。
        MockitoAnnotations.initMocks(this);
        //在这之后，你就可以放心大胆地使用when().then()等进行更详细的设置。
    }

    @Test
    public void test(){
        //aaaDao和BbbDao已经被mock了. 这里讲aaaDao的testA方法设为调用真实
        doCallRealMethod().when(aaaDao).testA();
        abcService.test();
    }
}
