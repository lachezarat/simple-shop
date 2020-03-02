package com.myproject.eshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Table(name = "smartwatches")
public class Smartwatch extends Product {

    private String display;
    private String centralProcessingUnit;
    private int storage;
    private int ram;
    private boolean hasCamera = false;
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
    @Min(value = 0, message = "The storage must be positive")
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Column(nullable = false)
    @Min(value = 0, message = "The RAM must be positive")
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Column(name = "has_camera")
    public boolean isHasCamera() {
        return hasCamera;
    }

    public void setHasCamera(boolean hasCamera) {
        this.hasCamera = hasCamera;
    }

    @Column(name = "battery_capacity", nullable = false)
    @Min(value = 0, message = "The battery capacity must be positive")
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
