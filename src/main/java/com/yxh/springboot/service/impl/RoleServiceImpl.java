package com.yxh.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yxh.springboot.entity.Menu;
import com.yxh.springboot.entity.Role;
import com.yxh.springboot.entity.Rolemenu;
import com.yxh.springboot.mapper.RoleMapper;
import com.yxh.springboot.mapper.RolemenuMapper;
import com.yxh.springboot.service.IMenuService;
import com.yxh.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-02-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RolemenuServiceImpl rolemenuService;
    @Resource
    private RolemenuMapper rolemenuMapper;
    @Resource
    private IMenuService menuService;
    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        LambdaQueryWrapper<Rolemenu> wrapper = Wrappers.<Rolemenu>lambdaQuery(Rolemenu.class).eq(Rolemenu::getRoleId, roleId);
        rolemenuService.remove(wrapper);
//        rolemenuService.deleteByRoleId(roleId);
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        //再把前端传过来的菜单id数组绑定到当前角色id上
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid()!=null&&!menuIdsCopy.contains(menu.getPid())){//二级菜单 并且传过来的menuId数组里没有父级
                //那么我们就得补上这个父级id
                Rolemenu rolemenu = new Rolemenu();
                rolemenu.setRoleId(roleId);
                rolemenu.setMenuId(menu.getPid());
                rolemenuService.save(rolemenu);
                menuIdsCopy.add(menu.getPid());
            }
            Rolemenu rolemenu = new Rolemenu();
            rolemenu.setRoleId(roleId);
            rolemenu.setMenuId(menuId);
            rolemenuService.save(rolemenu);

        }

    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {

        return rolemenuMapper.selectByRoleId(roleId);
    }
}
