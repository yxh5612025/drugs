package com.yxh.springboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-10
 */
@Getter
@Setter
@ApiModel(value = "Rolemenu对象", description = "")
public class Rolemenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer menuId;


}
