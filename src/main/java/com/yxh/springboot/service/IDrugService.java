package com.yxh.springboot.service;

import com.yxh.springboot.entity.Drug;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxh.springboot.entity.Ware;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-07
 */
public interface IDrugService extends IService<Drug> {

    List<Integer> findCounts(Drug drug);

    List<Ware> getWares();
}
