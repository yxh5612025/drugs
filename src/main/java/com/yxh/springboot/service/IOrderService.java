package com.yxh.springboot.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-27
 */
public interface IOrderService extends IService<Order> {



    void updateWare(Integer num,Drug drug);
}
