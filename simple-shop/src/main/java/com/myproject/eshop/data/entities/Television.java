package com.myproject.eshop.data.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "televisions")
public class Television extends BaseEntity {

    private int refreshRate;
    private double width;
    private double height;

    @Column(nullable = false)
    @Min(value = 0, message = "The refresh rate must be positive")
    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    @Column(nullable = false)
    @Min(value = 0, message = "The width must be positive")
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Column(nullable = false)
    @Min(value = 0, message = "The height must be positive")
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
