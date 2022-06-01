package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁希宏
 * @since 2022-02-28
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select id from role where keyid=#{role} ")
    Integer selectByKeyid(@Param("role") String role);
}
