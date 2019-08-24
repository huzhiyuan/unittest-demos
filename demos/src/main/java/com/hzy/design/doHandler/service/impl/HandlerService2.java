package com.hzy.design.doHandler.service.impl;

import com.hzy.design.doHandler.service.HandlerService;

public class HandlerService2 implements HandlerService {
    private static final Integer type = 2;

    @Override
    public String deal() {
        return "场景2的处理结果";
    }

    @Override
    public Integer getType() {
        return type;
    }
}
