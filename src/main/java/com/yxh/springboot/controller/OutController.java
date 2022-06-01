package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Dict;
import com.yxh.springboot.entity.Out;
import com.yxh.springboot.entity.User;
import com.yxh.springboot.mapper.OutMapper;
import com.yxh.springboot.service.impl.OutServiceImpl;
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
@RequestMapping("/out")
public class OutController extends BaseController{
    @Resource
    private OutServiceImpl outService;
    @Resource
    private OutMapper outMapper;


    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<Out> wrapper = Wrappers.<Out>lambdaQuery().like(Out::getName,search);
        Page<Out> page = outService.page(new Page<Out>(pn, pnSize), wrapper);
        return Result.success(page);

    }

    @PostMapping("/add")
    public Result<?> drugs(@RequestBody Out out) {
        User user = getUser();
        out.setUsername(user.getUsername());
        out.setTime(new Date());
        out.setTotalPrice(out.getUnitPrice().multiply(BigDecimal.valueOf(out.getAmount())));
        Integer create = outService.create(out);
        return Result.success(create);
    }
    @GetMapping("/findImg")
    public Result<?> findImg(@RequestParam("name") String name){
        String img = outMapper.selectName(name);
        return Result.success(img);
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody Out book){
        outService.updateById(book);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        outService.removeById(id);
        return Result.success();
    }

}
