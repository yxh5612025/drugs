package com.yxh.springboot.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hpsf.Decimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-07
 */
@Getter
@Setter
@ApiModel(value = "Drug对象", description = "")
public class Drug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("药品名称")
    private String name;

    @ApiModelProperty("化学名称")
    private String chemistry;

    @ApiModelProperty("规格")
    private String specif;

    @ApiModelProperty("药品类别")
    private String type;

    @ApiModelProperty("剂型")
    private String dosage;

    @ApiModelProperty("批号")
    private String batch;

    @ApiModelProperty("供应商")
    private String business;

    private Integer num;

    private String imgpath;

    private BigDecimal price;





}
