package com.yxh.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yxh.springboot.entity.Menu;
import com.yxh.springboot.mapper.MenuMapper;
import com.yxh.springboot.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> findMenus(String search) {
            LambdaQueryWrapper<Menu> wrapper = Wrappers.<Menu>lambdaQuery().like(Menu::getName, search);
            List<Menu> list = list(wrapper);
            //找出pid为null的一级菜单
            List<Menu> menus = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
            for (Menu menu : menus) {
                menu.setChildren(list.stream().filter(menu1 -> menu.getId().equals(menu1.getPid())).collect(Collectors.toList()));
            }
            return menus;
    }
}