package com.jaclon.springcloud.dao;

import com.jaclon.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author jaclon
 * @date 2020/3/8
 */
@Mapper
public interface PaymentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Payment getPaymentById(@Param("id") Long id);


    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 影响行数
     */
    int create(Payment payment);
}
