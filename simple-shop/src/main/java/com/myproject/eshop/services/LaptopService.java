package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.LaptopServiceModel;

import java.util.List;

public interface LaptopService {

    List<LaptopServiceModel> findAllLaptops();

    LaptopServiceModel createLaptop(LaptopServiceModel laptopServiceModel);

    LaptopServiceModel findByBrandAndModel(String brand, String model);

    LaptopServiceModel editLaptop(String brand, String model, LaptopServiceModel laptopServiceModel);
}
