package com.jaclon.springcloud.service;

import com.jaclon.springcloud.entity.Payment;

/**
 * @author jaclon
 * @date 2020/3/8
 */
public interface PaymentService {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Payment getPaymentById(Long id);


    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 影响行数
     */
    int create(Payment payment);
}
