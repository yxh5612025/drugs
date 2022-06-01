package com.yxh.springboot.mapper;

import com.yxh.springboot.controller.dto.UserPasswordDTO;
import com.yxh.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("update `user` set password =#{newPassword} where username=#{username} and password=#{password} ")
    int updatePassword(UserPasswordDTO user);

    @Select("SELECT  COUNT(*) FROM USER")
    int findCount();
}
