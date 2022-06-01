package com.yxh.springboot.controller;


import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxh.springboot.common.Result;
import com.yxh.springboot.controller.dto.UserDTO;
import com.yxh.springboot.controller.dto.UserPasswordDTO;
import com.yxh.springboot.entity.User;
import com.yxh.springboot.mapper.RoleMapper;
import com.yxh.springboot.mapper.UserMapper;
import com.yxh.springboot.service.impl.UserServiceImpl;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@RestController
@CrossOrigin
@RequestMapping("/TUser")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;


    @GetMapping("/list")
    public Result<?> list(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,
                          @RequestParam(value = "pnSize",defaultValue = "10")Integer pnSize,
                          @RequestParam(value = "search",defaultValue = "")String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().like(User::getUsername,search);
        Page<User> page = userService.page(new Page<User>(pn, pnSize), wrapper);

        return Result.success(page);

    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer  id) {
        return Result.success(userMapper.selectById(id));
    }
    @PostMapping("/add")
    public Result<?> books(@RequestBody User user){
        boolean save = userService.save(user);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> result(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id){
        userService.removeById(id);
        return Result.success();
    }
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserDTO user){

        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error("-1","参数错误");
        }
        UserDTO userDTO = userService.login(user);

        return Result.success(userDTO);
    }
    @GetMapping("/username/{username}")
    public Result<?> username(@PathVariable("username") String username){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery(User.class).eq(User::getUsername, username);
        User user = userService.getOne(wrapper);
        return Result.success(user);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserDTO user){
        User register = userService.register(user);
        return Result.success(register);


    }

    @GetMapping("/export")
    public void export(HttpServletResponse response){
        List<User> users = userService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        writer.addHeaderAlias("username","用户名");
//        writer.addHeaderAlias("password","密码");
//        writer.addHeaderAlias("nickname","昵称");
//        writer.addHeaderAlias("email","邮箱");
//        writer.addHeaderAlias("adress","地址");
        writer.write(users,true);

        response.setContentType("application/vnd.openxmlfornats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename = URLEncodeUtil.encode("用户信息", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition","attachment;filename="+filename+".xlsx");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.flush(outputStream,true);
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    @PostMapping("/import")
    public void imp(MultipartFile file){
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> users = reader.readAll(User.class);
        userService.saveBatch(users);
    }
    @PostMapping("/password")
    public Result<?> updatePassword(@RequestBody UserPasswordDTO userPasswordDTO){
        userPasswordDTO.setPassword(SecureUtil.md5(userPasswordDTO.getPassword()));
        userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
         userService.updatePassword(userPasswordDTO);
        System.out.println("1111111111");
        System.out.println("1111111111");
        System.out.println("1111111111");
        System.out.println("master test");
        return Result.success();
    }
}

