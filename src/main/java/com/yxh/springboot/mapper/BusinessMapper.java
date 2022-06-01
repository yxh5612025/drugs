package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Business;
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
 * @since 2022-03-21
 */
@Mapper
public interface BusinessMapper extends BaseMapper<Business> {

    @Select("select id,name from business")
    List<Business> selectBusiness();
}
