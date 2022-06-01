package com.yxh.springboot.mapper;

import com.yxh.springboot.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-27
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Update("update `t_order` set state = #{state},pay_time = #{payTime} where order_no = #{tradeNo}")
    int updateState(@Param("tradeNo") String tradeNo, @Param("state") int state, @Param("payTime") String payTime);

    @Select("SELECT  COUNT(*) FROM t_order")
    int findCount();

    @Select("select num from drug where id=#{id} ")
    Integer findNum(@Param("id") Integer id);
}
