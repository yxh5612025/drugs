package com.yxh.springboot.service;

import com.yxh.springboot.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
public interface IDictService extends IService<Dict> {

    List<Dict> selectType(Dict dict);
}
