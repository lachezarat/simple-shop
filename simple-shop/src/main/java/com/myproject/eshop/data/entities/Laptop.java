package com.myproject.eshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "laptops")
public class Laptop extends Product {

    private String display;
    private String centralProcessingUnit;
    private String graphicsProcessingUnit;
    private int storage;
    private int ram;
    private double weight;


    @Column(nullable = false)
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Column(name = "CPU", nullable = false)
    public String getCentralProcessingUnit() {
        return centralProcessingUnit;
    }

    public void setCentralProcessingUnit(String centralProcessingUnit) {
        this.centralProcessingUnit = centralProcessingUnit;
    }

    @Column(name = "GPU", nullable = false)
    public String getGraphicsProcessingUnit() {
        return graphicsProcessingUnit;
    }

    public void setGraphicsProcessingUnit(String graphicsProcessingUnit) {
        this.graphicsProcessingUnit = graphicsProcessingUnit;
    }

    @Column(nullable = false)
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Column(nullable = false)
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Column(nullable = false)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
