package com.myproject.eshop.services;

import com.myproject.eshop.data.models.binding.SmartphoneCreateBindingModel;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;

import java.util.List;

public interface SmartphoneService {

    List<SmartphoneServiceModel> findAllSmartphones();

    SmartphoneServiceModel createSmartphone(SmartphoneServiceModel smartphoneServiceModel);

    SmartphoneServiceModel findByBrandAndModel(String brand, String model);
}
