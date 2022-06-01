package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Business;
import com.yxh.springboot.entity.Dict;
import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.entity.Ware;
import com.yxh.springboot.mapper.BusinessMapper;
import com.yxh.springboot.mapper.DrugMapper;
import com.yxh.springboot.service.impl.DictServiceImpl;
import com.yxh.springboot.service.impl.DrugServiceImpl;
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
 * @since 2022-03-07
 */
@CrossOrigin
@RestController
@RequestMapping("/drug")
public class DrugController {
    @Resource
    private DrugServiceImpl drugService;

    @Resource
    private DictServiceImpl dictService;
    @Resource
    private DrugMapper drugMapper;
    @Resource
    private BusinessMapper businessMapper;

    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize", defaultValue = "10") Integer pnSize,
                          @RequestParam(value = "search", defaultValue = "") String search) {
        LambdaQueryWrapper<Drug> wrapper = Wrappers.<Drug>lambdaQuery().like(Drug::getName, search);
        Page<Drug> page = drugService.page(new Page<Drug>(pn, pnSize), wrapper);
        return Result.success(page);
    }

    @GetMapping("getDrugs")
    public Result<?> getDrugs(){
        List<Drug> list = drugService.list();
        return Result.success(list);
    }

    @PostMapping("/addDrug")
    public Result<?> drugs(@RequestBody Drug drug) {
        boolean save = drugService.save(drug);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> result(@RequestBody Drug drug) {
        drugService.updateById(drug);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id) {
        drugService.removeById(id);
        return Result.success();
    }

    @PutMapping("/store")
    public Result<?> store(@RequestParam("id") Integer id) {
        drugMapper.updateStoreById(id);
        return Result.success();
    }

    @GetMapping("/type")
    public Result<?> getType() {
        LambdaQueryWrapper<Dict> wrapper = Wrappers.<Dict>lambdaQuery(Dict.class).eq(Dict::getType, "type");
        List<Dict> list = dictService.list(wrapper);
        return Result.success(list);
    }

    @GetMapping("/dosage")
    public Result<?> getDosage() {
        LambdaQueryWrapper<Dict> wrapper = Wrappers.<Dict>lambdaQuery(Dict.class).eq(Dict::getType, "dosage");
        List<Dict> list = dictService.list(wrapper);
        return Result.success(list);
    }

    @GetMapping("/business")
    public Result<?> getBusiness() {
        List<Business> list = businessMapper.selectBusiness();
        return Result.success(list);
    }
}

