package com.hzy.design.doHandler.service.impl;

import com.hzy.design.doHandler.service.HandlerService;

public class HandlerService1 implements HandlerService {
    private static final Integer type = 1;

    @Override
    public String deal() {
        return "场景1的处理结果";
    }

    @Override
    public Integer getType() {
        return type;
    }
}
