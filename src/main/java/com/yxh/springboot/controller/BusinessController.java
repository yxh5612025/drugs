package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Business;
import com.yxh.springboot.service.impl.BusinessServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
@RequestMapping("/business")
public class BusinessController {
    @Resource
    private BusinessServiceImpl businessService;

    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<Business> wrapper = Wrappers.<Business>lambdaQuery().like(Business::getName,search);
        Page<Business> page = businessService.page(new Page<Business>(pn, pnSize), wrapper);
        return Result.success(page);

    }

    @PostMapping("/addBusiness")
    public Result<?> businesss(@RequestBody Business business){
        boolean save = businessService.save(business);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody Business business){
        businessService.updateById(business);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        businessService.removeById(id);
        return Result.success();
    }
}
