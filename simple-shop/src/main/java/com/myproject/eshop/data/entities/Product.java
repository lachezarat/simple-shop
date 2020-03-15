package com.myproject.eshop.data.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public class Product extends BaseEntity {

    private String brand;
    private String model;
    private String imgUrl;
    private BigDecimal price;

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "image_url", nullable = false)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
