package com.yxh.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.entity.In;
import com.yxh.springboot.entity.Ware;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-22
 */
@Mapper
public interface WareMapper extends BaseMapper<Ware> {

    @Update("update ware set num = num +#{num} where name = #{name}")
    void addWare(@Param("num") Integer num, @Param("name") String name);

    @Update("update ware set num = num -#{amount} where name = #{name}")
    void reduceWare(@Param("amount") Integer amount,@Param("name") String name);

    @Update("update ware set num = num -#{num} where name = #{name}")
    void updateWare(@Param("num") Integer num, @Param("name") String name);

    @Select("select num from ware where name= #{name} ")
    Integer selectWareByName(@Param("name") String name);

    @Insert("insert into ware(NAME,num,business,img,description) values(#{name},#{num},#{business},#{img},#{description})")
    void insertDrug(@Param("name")String name,@Param("num")Integer num,@Param("business")String business,@Param("img")String img,
                    @Param("description")String description);

    @Select("select * from ware where name=#{name} and business=#{business}")
    Ware selectWare(@Param("name") String name,@Param("business") String business);
}
