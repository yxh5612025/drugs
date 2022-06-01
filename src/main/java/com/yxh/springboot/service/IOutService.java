package com.yxh.springboot.service;

import com.yxh.springboot.entity.Out;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
public interface IOutService extends IService<Out> {

    Integer create(Out out);



}
