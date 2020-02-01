package com.myproject.eshop.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "televisions")
public class Television extends BaseEntity {

    private double displaySize;
    private int refreshRate;
    private double width;
    private double height;

    @Column(name = "display_size", nullable = false)
    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

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
