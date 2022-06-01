package com.yxh.springboot.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.*;

import com.yxh.springboot.mapper.DrugMapper;
import com.yxh.springboot.mapper.OrderMapper;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.impl.OrderServiceImpl;
import com.yxh.springboot.service.impl.WareServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-27
 */
    @CrossOrigin
    @RestController
    @RequestMapping("/order")
    public class OrderController extends BaseController {

        @Resource
        private OrderMapper OrderMapper;

        @Resource
        DrugMapper drugMapper;

        @Resource
        OrderServiceImpl orderService;

        @Resource
        WareMapper wareMapper;

    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery().like(Order::getName,search);
        Page<Order> page = orderService.page(new Page<Order>(pn, pnSize), wrapper);
        return Result.success(page);
    }
    @GetMapping("/listUser")
    public Result<?> listUser(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search,
                          @RequestParam(value = "userId")Integer userId){
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery().orderByDesc(Order::getId).eq(Order::getUserId,userId).like(Order::getName,search);
        Page<Order> page = orderService.page(new Page<Order>(pn, pnSize), wrapper);
        return Result.success(page);

    }

    @PostMapping("/add")
    public Result<?> save(@RequestBody Order order){
        boolean save = orderService.save(order);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        orderService.removeById(id);
        return Result.success();
    }

        @GetMapping("/{id}")
        public Result<?> getById(@PathVariable Long id) {
            return Result.success(OrderMapper.selectById(id));
        }
        @PutMapping("/update")
            public Result<?> result(@RequestBody Order order){
            orderService.updateById(order);
            return Result.success();
    }
        @GetMapping("/buy/{drugId}/{num}")
        public Result<?> buy(@PathVariable Long drugId,@PathVariable Integer num) {
            Drug drug = drugMapper.selectById(drugId);
            String orderNo = IdUtil.getSnowflake().nextIdStr();
            String payUrl = "http://localhost:8181/alipay/pay?subject=" + drug.getName() + "&traceNo=" + orderNo + "&totalAmount=" + drug.getPrice().multiply(BigDecimal.valueOf(num));

            User user = getUser();
            Order order = new Order();
            order.setOrderNo(orderNo);
            order.setNum(num);
            order.setTotalPrice(drug.getPrice().multiply(BigDecimal.valueOf(num)));
            order.setPayPrice(drug.getPrice().multiply(BigDecimal.valueOf(num)));
            order.setTransportPrice(BigDecimal.ZERO);
            order.setUserId(user.getId());
            order.setDrugId(drug.getId());
            order.setUsername(user.getUsername());
            order.setName(drug.getName());
            order.setDiscount(BigDecimal.ZERO);
            order.setPayTime(order.getCreateTime());
            save(order);
             //新建订单，扣减库存
            orderService.updateWare(num,drug);
            Integer nums = wareMapper.selectWareByName(order.getName());
            if (nums<order.getNum()){
               throw new SecurityException("库存不足");
            }
            return Result.success(payUrl);

        }


    
    }

