package com.jaclon.springcloud.service.impl;

import com.jaclon.springcloud.dao.PaymentDao;
import com.jaclon.springcloud.entity.Payment;
import com.jaclon.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jaclon
 * @date 2020/3/8
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }
}
