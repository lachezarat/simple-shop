package com.myproject.eshop.data.models.view;

import java.math.BigDecimal;

public class LaptopAllViewModel {

    private String brand;
    private String model;
    private BigDecimal price;
    private String centralProcessingUnit;
    private String graphicsProcessingUnit;
    private int storage;
    private int ram;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCentralProcessingUnit() {
        return centralProcessingUnit;
    }

    public void setCentralProcessingUnit(String centralProcessingUnit) {
        this.centralProcessingUnit = centralProcessingUnit;
    }

    public String getGraphicsProcessingUnit() {
        return graphicsProcessingUnit;
    }

    public void setGraphicsProcessingUnit(String graphicsProcessingUnit) {
        this.graphicsProcessingUnit = graphicsProcessingUnit;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
