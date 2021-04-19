package com.jaclon.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jaclon.springcloud.entity.CommonResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jaclon
 * @date 2021/4/19
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---2");
    }
}
