package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Dict;
import com.yxh.springboot.entity.Menu;
import com.yxh.springboot.entity.Role;
import com.yxh.springboot.service.impl.DictServiceImpl;
import com.yxh.springboot.service.impl.MenuServiceImpl;
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
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-01
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuServiceImpl MenuService;
    @Resource
    private DictServiceImpl dictService;
//    @GetMapping("/list")
//    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
//                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
//                          @RequestParam(value = "search",defaultValue = "")String search){
//        LambdaQueryWrapper<Menu> wrapper = Wrappers.<Menu>lambdaQuery().like(Menu::getName,search);
//        Page<Menu> page = MenuService.page(new Page<Menu>(pn, pnSize), wrapper);
//        return Result.success(page);
//
//    }
 @GetMapping("/list")
    public Result<?> list(
                          @RequestParam(value = "search",defaultValue = "")String search){

     List<Menu> menus = MenuService.findMenus(search);

     return Result.success(menus);
 }

    @PostMapping("/add")
    public Result<?> books(@RequestBody Menu Menu){
        boolean save = MenuService.save(Menu);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody Menu Menu){
        MenuService.updateById(Menu);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        MenuService.removeById(id);
        return Result.success();
    }
    @GetMapping("/icons")
    public Result<?> getIcon(){
        LambdaQueryWrapper<Dict> wrapper = Wrappers.<Dict>lambdaQuery(Dict.class).eq(Dict::getType, "icon");
        List<Dict> list = dictService.list(wrapper);
        return Result.success(list);
    }
    @GetMapping("/ids")
    public Result<?> findAllIds(){
     return Result.success(MenuService.list().stream().map(Menu::getId));
    }
}
