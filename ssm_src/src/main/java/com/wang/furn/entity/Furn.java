package com.wang.furn.entity;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class Furn {
    private Integer id;

    private String name;

    private String maker;

    private BigDecimal price;

    private Integer stock;

    private String imgPath="/images";

    public Furn(String name, String maker, BigDecimal price, Integer stock, String imgPath) {
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.stock = stock;
        if(StringUtils.hasText(imgPath))
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}