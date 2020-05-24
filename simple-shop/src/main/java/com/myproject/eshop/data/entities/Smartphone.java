package com.myproject.eshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "smartphones")
public class Smartphone extends Product {

    private String display;
    private String centralProcessingUnit;
    private String camera;
    private int batteryCapacity;
    private int storage;
    private int ram;
    private boolean hasMemoryCardSlot = false;
    private double weight;


    @Column(name = "display", nullable = false)
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

    @Column(name = "camera", nullable = false)
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

    @Column(name = "storage", nullable = false)
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Column(name = "ram", nullable = false)
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Column(name = "has_memory_card_slot", nullable = false)
    public boolean isHasMemoryCardSlot() {
        return hasMemoryCardSlot;
    }

    public void setHasMemoryCardSlot(boolean hasMemoryCardSlot) {
        this.hasMemoryCardSlot = hasMemoryCardSlot;
    }

    @Column(name = "weight", nullable = false)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
