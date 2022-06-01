package com.yxh.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.entity.Ware;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.IWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-22
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements IWareService {


}
