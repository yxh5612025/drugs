package com.yxh.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yxh.springboot.controller.dto.UserDTO;
import com.yxh.springboot.controller.dto.UserPasswordDTO;
import com.yxh.springboot.entity.Menu;
import com.yxh.springboot.entity.User;
import com.yxh.springboot.exception.ServiceException;
import com.yxh.springboot.mapper.RoleMapper;
import com.yxh.springboot.mapper.RolemenuMapper;
import com.yxh.springboot.mapper.UserMapper;
import com.yxh.springboot.service.IMenuService;
import com.yxh.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import utils.TokenUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Log log = Log.get();
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolemenuMapper rolemenuMapper;
    @Resource
    private IMenuService menuService;
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDTO login(UserDTO userDTO) {
        // 用户密码 md5加密
        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, userDTO.getUsername()).eq(User::getPassword, userDTO.getPassword());
       User user;
        try {
            user = getOne(wrapper);
        }catch(Exception e) {
            log.error(e);
            throw new ServiceException("500","系统错误");
        }
            if (user != null) {
                BeanUtil.copyProperties(user, userDTO, true);

                String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
                userDTO.setToken(token);
                String role = user.getRole();
                Integer roleId = roleMapper.selectByKeyid(role);

                //设置用户菜单列表
                List<Menu> roleMenus = getRoleMenu(roleId);
                userDTO.setMenus(roleMenus);
                return userDTO;
            } else {
                throw new ServiceException("600", "用户名或密码错误");
            }
        }

    @Override
    public User register(UserDTO user) {
        // 用户密码 md5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery(User.class).eq(User::getUsername, user.getUsername());
       User tUser;
        try {
            tUser = getOne(wrapper);
        }catch(Exception e) {
            log.error(e);
            throw new ServiceException("500","系统错误");
        }
        if (tUser==null){
            tUser =new User();
            BeanUtil.copyProperties(user, tUser, true);
            save(tUser);
        }else {
            throw new ServiceException("600","用户已存在");
        }
        return tUser;
    }

    @Override
    public void updatePassword(UserPasswordDTO user) {
        int update = userMapper.updatePassword(user);
        if (update < 1) {
            throw new ServiceException("600", "密码错误");
        }
    }
    /**
     * 获取当前角色的菜单列表
     * @param roleId
     * @return
     */
    private List<Menu> getRoleMenu(Integer roleId){
        //当前角色的所有菜单id集合
        List<Integer> menuids = rolemenuMapper.selectByRoleId(roleId);
        //查出系统所有菜单
        List<Menu> menus = menuService.findMenus("");
        //new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        //筛选当前用户的菜单
        for (Menu menu : menus) {
            if (menuids.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child->!menuids.contains(child.getId()));
        }
        return roleMenus;
    }
}

