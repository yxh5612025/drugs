package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.entity.Ware;
import com.yxh.springboot.mapper.DrugMapper;
import com.yxh.springboot.service.IDrugService;
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
 * @since 2022-03-07
 */
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements IDrugService {

    @Resource DrugMapper drugMapper;
    @Override
    public List<Integer> findCounts(Drug drug) {
        return drugMapper.findCounts();
    }

    @Override
    public List<Ware> getWares() {
        drugMapper.findWare();
        return null;
    }
}
