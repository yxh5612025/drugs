package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Drug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-07
 */
@Mapper
public interface DrugMapper extends BaseMapper<Drug> {

    @Select("SELECT d.`name`,d.`imgpath`,w.`business`,w.`description`,w.`num` FROM drug d JOIN ware w")
    List<Drug> listByDrugs();

    @Update("update drug set num=num-1 where id=#{id}  ")
    void updateStoreById(@Param("id") Integer id);

    @Select("SELECT  COUNT(*) FROM drug GROUP BY TYPE")
    List<Integer> findCounts();

    @Select("SELECT  COUNT(*) FROM drug")
    int findCount();

    @Select("select num from ware ")
    void findWare();

    @Select("select id,name from drug")
    List<Drug> selectDrug();
}
