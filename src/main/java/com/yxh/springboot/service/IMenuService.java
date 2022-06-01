package com.yxh.springboot.service;

import com.yxh.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-01
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String search);
}
