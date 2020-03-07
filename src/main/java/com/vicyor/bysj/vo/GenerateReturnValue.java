package com.vicyor.bysj.vo;

import lombok.Data;

import java.util.Map;

/**
 * 作者:姚克威
 * 时间:2020/3/7 21:50
 **/
@Data
public class GenerateReturnValue {
    private Integer code;
    private String msg;
    private Map<String,Object>data;

    public GenerateReturnValue(Integer code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
