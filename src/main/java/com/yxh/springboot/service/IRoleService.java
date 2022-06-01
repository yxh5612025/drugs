package com.yxh.springboot.service;

import com.yxh.springboot.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-02-28
 */
public interface IRoleService extends IService<Role> {

    void setRoleMenu(Integer roleId, List<Integer> menuId);

    List<Integer> getRoleMenu(Integer roleId);
}
