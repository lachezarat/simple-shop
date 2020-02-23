package com.myproject.eshop.services;

import com.myproject.eshop.data.entities.Smartwatch;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;

import java.util.List;

public interface SmartwatchService {

    List<SmartwatchServiceModel> findAllSmartwatches();

    SmartwatchServiceModel createSmartwatch(SmartwatchServiceModel smartwatchServiceModel);

    SmartwatchServiceModel findByBrandAndModel(String brand, String model);

    SmartwatchServiceModel editSmartwatch(String brand, String model, SmartwatchServiceModel smartwatchServiceModel);
}
