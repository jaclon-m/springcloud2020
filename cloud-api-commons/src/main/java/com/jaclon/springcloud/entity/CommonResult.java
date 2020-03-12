package com.jaclon.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jaclon
 * @date 2020/3/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult <T> {

    private Integer code;
    private String message;
    private T      data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
