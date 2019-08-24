package com.hzy.design.doHandler.service.impl;

import com.hzy.design.doHandler.service.HandlerService;

public class HandlerService3 implements HandlerService {
    private static final Integer type = 3;
    @Override
    public String deal() {
        return "场景3的处理结果";
    }

    @Override
    public Integer getType() {
        return type;
    }
}
