package com.myproject.eshop.services;

import com.myproject.eshop.data.entities.Smartwatch;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;

import java.security.Principal;
import java.util.List;

public interface SmartwatchService {

    List<SmartwatchServiceModel> findAllSmartwatches();

    SmartwatchServiceModel createSmartwatch(SmartwatchServiceModel smartwatchServiceModel, Principal principal);

    SmartwatchServiceModel findByBrandAndModel(String brand, String model);

    SmartwatchServiceModel editSmartwatch(String brand, String model, SmartwatchServiceModel smartwatchServiceModel, Principal principal);

    void deleteSmartwatch(String brand, String model, Principal principal);
}
