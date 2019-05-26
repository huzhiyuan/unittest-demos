package com.hzy.unittest.demos;

import com.hzy.unittest.dao.AaaDao;
import com.hzy.unittest.dao.BbbDao;

import javax.annotation.Resource;

public class MockitoAnnotationsDemos {
    @Resource(name="aaaDao")
    private AaaDao aaaDao;

    @Resource(name="bbbDao")
    private BbbDao bbbDao;

//注：此处省略的代码中并不包含aaaDao和bbbDao的setter方法
    public void test(){
        aaaDao.testA();
        bbbDao.testB();
    }
}
