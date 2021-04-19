package com.jalcon.springcloud.service;

import com.jaclon.springcloud.entity.CommonResult;
import com.jaclon.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/4/19 15:30
 */
@Service
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "fallback");
    }
}
