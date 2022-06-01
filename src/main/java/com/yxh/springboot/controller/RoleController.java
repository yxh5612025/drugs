package com.yxh.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.entity.Role;
import com.yxh.springboot.service.impl.RoleServiceImpl;
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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁希宏
 * @since 2022-02-28
 */
@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleServiceImpl roleService;
    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().like(Role::getName,search);
        Page<Role> page = roleService.page(new Page<Role>(pn, pnSize), wrapper);
        return Result.success(page);
    }
    @GetMapping()
    public Result<?> findAll(){
        return Result.success(roleService.list());
    }

    @PostMapping("/add")
    public Result<?> books(@RequestBody Role role){
        boolean save = roleService.save(role);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody Role role){
        roleService.updateById(role);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        roleService.removeById(id);
        return Result.success();
    }

    @PostMapping("/roleMenu/{roleId}")
    public Result<?> roleMenu(@PathVariable("roleId") Integer roleId, @RequestBody List<Integer> menuId){
        roleService.setRoleMenu( roleId,menuId);
        return Result.success();
    }
    @GetMapping("/roleMenu/{roleId}")
    public Result<?> getRoleMenu(@PathVariable("roleId") Integer roleId){
        List<Integer> list = roleService.getRoleMenu(roleId);
        return Result.success(list);
    }
}
