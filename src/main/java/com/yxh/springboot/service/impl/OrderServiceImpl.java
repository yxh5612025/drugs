package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.entity.Order;
import com.yxh.springboot.mapper.OrderMapper;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private WareMapper wareMapper;


    @Override
    public void updateWare(Integer num,Drug drug) {
        wareMapper.updateWare(num,drug.getName());
    }
}
