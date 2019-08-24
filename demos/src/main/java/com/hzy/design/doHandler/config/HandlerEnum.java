package com.hzy.design.doHandler.config;


import lombok.AllArgsConstructor;
import lombok.Getter;

public enum HandlerEnum {
    A_TYPE(1,2),
    B_TYPE(2,3),
    C_TYPE(3,4);
    private Integer code;
    private Integer value;

    HandlerEnum(int code, int value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
