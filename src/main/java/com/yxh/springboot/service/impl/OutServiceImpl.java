package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.Out;
import com.yxh.springboot.exception.ServiceException;
import com.yxh.springboot.mapper.OutMapper;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.IOutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
@Service
public class OutServiceImpl extends ServiceImpl<OutMapper, Out> implements IOutService {

    @Resource
    private OutMapper outMapper;
    @Resource
    private WareMapper wareMapper;
    @Override
    public Integer create(Out out) {

            Integer num = wareMapper.selectWareByName(out.getName());
            if (num>out.getAmount()){
            int insert = outMapper.insert(out);
            wareMapper.reduceWare(out.getAmount(),out.getName());
            }
        return num;


    }


}
