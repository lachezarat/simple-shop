package com.myproject.eshop.data.models.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public abstract class BaseProductCreateBindingModel {

    private String brand;
    private String model;
    private String imgUrl;
    private BigDecimal price;

    @NotEmpty(message = "Brand is required!")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @NotEmpty(message = "Model is required!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotEmpty(message = "Image URL is required!")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Min(value = 0, message = "The price must be positive")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
