package com.yxh.springboot.service;

import com.yxh.springboot.controller.dto.UserDTO;
import com.yxh.springboot.controller.dto.UserPasswordDTO;
import com.yxh.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface UserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO user);

    void updatePassword(UserPasswordDTO user);
}
