package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.News;
import com.yxh.springboot.service.impl.NewsServiceImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-20
 */
@RestController
@CrossOrigin
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsServiceImpl newsService;
    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery().like(News::getContent,search);
        Page<News> page = newsService.page(new Page<News>(pn, pnSize), wrapper);
        return Result.success(page);

    }

    @PostMapping("/add")
    public Result<?> books(@RequestBody News book){
        boolean save = newsService.save(book);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody News book){
        newsService.updateById(book);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        newsService.removeById(id);
        return Result.success();
    }
}

