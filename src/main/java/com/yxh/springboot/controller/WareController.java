package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.entity.Ware;
import com.yxh.springboot.mapper.DrugMapper;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.impl.DictServiceImpl;
import com.yxh.springboot.service.impl.DrugServiceImpl;
import com.yxh.springboot.service.impl.WareServiceImpl;
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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-22
 */
@CrossOrigin
@RestController
@RequestMapping("/ware")
public class WareController {
    @Resource
    private WareServiceImpl wareService;
    @Resource
    private DrugMapper drugMapper;
    @Resource
    private WareMapper wareMapper;
    @Resource
    private DrugServiceImpl drugService;
    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<Ware> wrapper = Wrappers.<Ware>lambdaQuery().like(Ware::getName,search);
        Page<Ware> page = wareService.page(new Page<Ware>(pn, pnSize), wrapper);
        return Result.success(page);

    }

    @PostMapping("/add")
    public Result<?> dicts(@RequestBody Ware ware){
        boolean save = wareService.save(ware);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody Ware ware){
       wareService.updateById(ware);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        wareService.removeById(id);
        return Result.success();
    }
    @GetMapping("/message")
    public Result<?> message(){
        List<Drug> drugs = drugMapper.listByDrugs();
        return Result.success(drugs);
    }
//    @PutMapping("/inStore")
//    public  Result<?> inStore(@RequestBody Ware ware){
//
//        wareMapper.updateByNum()
//    }
    }
