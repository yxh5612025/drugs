package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.DrugType;
import com.yxh.springboot.mapper.DrugTypeMapper;
import com.yxh.springboot.service.IDrugTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-04-27
 */
@Service
public class DrugTypeServiceImpl extends ServiceImpl<DrugTypeMapper, DrugType> implements IDrugTypeService {

}
