package com.yxh.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Dict;
import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.mapper.DrugMapper;
import com.yxh.springboot.mapper.OrderMapper;
import com.yxh.springboot.mapper.UserMapper;
import com.yxh.springboot.service.impl.DictServiceImpl;
import com.yxh.springboot.service.impl.DrugServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private DrugServiceImpl drugService;
    @Resource
    private DictServiceImpl dictService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private DrugMapper drugMapper;
    @GetMapping("/example")
    public Result<?> get(Drug drug){
        Map<String,Object> map = new HashMap<>();
//        List<Dict> dicts = dictService.selectType(dict);
        LambdaQueryWrapper<Dict> wrapper = Wrappers.lambdaQuery(Dict.class).eq(Dict::getType, "type");
        List<Dict> dicts = dictService.list(wrapper);
        List<Integer> counts= drugService.findCounts(drug);
        //List<String > list = bookService.list().stream().map(Drug::getAuthor).collect(Collectors.toList());
        map.put("x", dicts);
        map.put("y",counts);
        return Result.success(map);
    }
    @GetMapping("user")
    public Result<?> user(){
        int count = userMapper.findCount();
        return Result.success(count);
    }

    @GetMapping("order")
    public Result<?> order(){
        int count = orderMapper.findCount();
        return Result.success(count);
    }
    @GetMapping("drug")
    public Result<?> drug(){
        int count = drugMapper.findCount();
        return Result.success(count);
    }
}
