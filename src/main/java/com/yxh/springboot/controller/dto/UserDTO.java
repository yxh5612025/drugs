package com.yxh.springboot.controller.dto;

import com.yxh.springboot.entity.In;
import com.yxh.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String token;
    private String avatarUrl;
    private String  sex;
    private String adress;
    private String email;
    private String role;
    private List<Menu> menus;
}
