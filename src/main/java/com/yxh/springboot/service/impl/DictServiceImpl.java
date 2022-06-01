package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.Dict;
import com.yxh.springboot.mapper.DictMapper;
import com.yxh.springboot.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private DictMapper dictMapper;
    @Override
    public List<Dict> selectType(Dict dict) {
        String type = dict.getType();
        List<Dict> dicts = dictMapper.selectType(type);
        return dicts;
    }
}
