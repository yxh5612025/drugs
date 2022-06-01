package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

    @Select("SELECT `name`FROM dict WHERE TYPE=#{type} ")
    List<Dict> selectType(@Param("type") String type);
}
