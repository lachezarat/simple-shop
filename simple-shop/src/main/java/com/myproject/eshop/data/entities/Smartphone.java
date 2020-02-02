package com.myproject.eshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "smartphones")
public class Smartphone extends BaseEntity {

    private String centralProcessingUnit;
    private String camera;
    private int batteryCapacity;
    private int storage;
    private int ram;
    private boolean hasMemoryCardSlot = false;
    private double weight;

    @Column(name = "CPU", nullable = false)
    public String getCentralProcessingUnit() {
        return centralProcessingUnit;
    }

    public void setCentralProcessingUnit(String centralProcessingUnit) {
        this.centralProcessingUnit = centralProcessingUnit;
    }

    @Column(nullable = false)
    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    @Column(name = "battery_capacity", nullable = false)
    @Min(value = 0, message = "The battery capacity must be positive")
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
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

    @Column(name = "has_memory_card_slot")
    public boolean isHasMemoryCardSlot() {
        return hasMemoryCardSlot;
    }

    public void setHasMemoryCardSlot(boolean hasMemoryCardSlot) {
        this.hasMemoryCardSlot = hasMemoryCardSlot;
    }

    @Column(nullable = false)
    @Min(value = 0, message = "The weight must be positive")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
