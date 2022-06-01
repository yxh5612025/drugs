package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Rolemenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-10
 */
@Mapper
public interface RolemenuMapper extends BaseMapper<Rolemenu> {


    @Select("select menu_id from rolemenu where role_id=#{roleId} ;")
    List<Integer> selectByRoleId(Integer roleId);
}
