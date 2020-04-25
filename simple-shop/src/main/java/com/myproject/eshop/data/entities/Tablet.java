package com.myproject.eshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tablets")
public class Tablet extends Product {

    private String display;
    private String centralProcessingUnit;
    private int storage;
    private int ram;
    private String camera;
    private int batteryCapacity;


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
    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    @Column(name = "battery_capacity", nullable = false)
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
