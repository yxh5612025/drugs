package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.Business;
import com.yxh.springboot.mapper.BusinessMapper;
import com.yxh.springboot.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

}
