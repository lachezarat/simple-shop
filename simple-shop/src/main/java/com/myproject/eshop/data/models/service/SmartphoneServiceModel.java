package com.myproject.eshop.data.models.service;

public class SmartphoneServiceModel extends ProductServiceModel {

    private String display;
    private String centralProcessingUnit;
    private String camera;
    private int batteryCapacity;
    private int storage;
    private int ram;
    private boolean hasMemoryCardSlot = false;
    private double weight;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCentralProcessingUnit() {
        return centralProcessingUnit;
    }

    public void setCentralProcessingUnit(String centralProcessingUnit) {
        this.centralProcessingUnit = centralProcessingUnit;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
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

    public boolean isHasMemoryCardSlot() {
        return hasMemoryCardSlot;
    }

    public void setHasMemoryCardSlot(boolean hasMemoryCardSlot) {
        this.hasMemoryCardSlot = hasMemoryCardSlot;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
