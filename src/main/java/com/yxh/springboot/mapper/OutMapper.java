package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Out;
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
 * @since 2022-03-21
 */
@Mapper
public interface OutMapper extends BaseMapper<Out> {

    @Select("select imgpath from drug where name=#{name}")
    String  selectName(@Param("name")String name);
}
