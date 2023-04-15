package com.example.springboot_furn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @TableName furn
 */
@TableName(value ="furn")
@Data
public class Furn implements Serializable {
    
    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "请输入家具名")
    private String name;

    @NotEmpty(message = "请输入厂商")
    private String maker;

    @NotNull(message = "请输入数字")
    @Range(min = 0,message = "请输入不小于零的数字")
    private BigDecimal price;

    private Integer stock;

    private String imgPath;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}