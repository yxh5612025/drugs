package com.yxh.springboot.service;

import com.yxh.springboot.entity.In;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxh.springboot.entity.Ware;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
public interface IInService extends IService<In> {

//
//    void updateWare(Long id);



    boolean create(In in,Ware ware);
}
