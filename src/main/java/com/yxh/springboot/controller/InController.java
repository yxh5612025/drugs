package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.*;
import com.yxh.springboot.mapper.DrugMapper;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.impl.DictServiceImpl;
import com.yxh.springboot.service.impl.DrugServiceImpl;
import com.yxh.springboot.service.impl.InServiceImpl;
import com.yxh.springboot.service.impl.WareServiceImpl;
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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
@CrossOrigin
@RestController
@RequestMapping("/in")
public class InController extends BaseController {
    @Resource
    private InServiceImpl inService;
    @Resource
    private DictServiceImpl dictService;
    @Resource
    private WareMapper wareMapper;
    @Resource
    private DrugMapper drugMapper;
    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<In> wrapper = Wrappers.<In>lambdaQuery().like(In::getName,search);
        Page<In> page = inService.page(new Page<In>(pn, pnSize), wrapper);
        return Result.success(page);

    }

    @PostMapping("/add")
    public Result<?> ins(@RequestBody In in){
        in.setTime(new Date());
        User user = getUser();
        Ware ware = wareMapper.selectWare(in.getName(),in.getBusiness());
        in.setUsername(user.getUsername());
        in.setTotalPrice(in.getUnitPrice().multiply(BigDecimal.valueOf(in.getAmount())));
        boolean create = inService.create(in,ware);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody In in){
        inService.updateById(in);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        inService.removeById(id);
        return Result.success();
    }
    @GetMapping("/business")
    public Result<?> getBusiness(){
        LambdaQueryWrapper<Dict> wrapper = Wrappers.<Dict>lambdaQuery(Dict.class).eq(Dict::getType, "business");
        List<Dict> list = dictService.list(wrapper);
        return Result.success(list);
    }
    @GetMapping("/drugs")
    public Result<?> getDrug() {
        List<Drug> list = drugMapper.selectDrug();
        return Result.success(list);
    }


//    @PutMapping("/updateWare/{id}")
//    public Result<?> updateWare(@PathVariable Long id){
//        inService.updateWare(id);
//        return Result.success();
//    }

}
