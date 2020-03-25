package com.myproject.eshop.services;

import com.myproject.eshop.data.models.binding.SmartphoneCreateBindingModel;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

public interface SmartphoneService {

    List<SmartphoneServiceModel> findAllSmartphones();

    SmartphoneServiceModel createSmartphone(SmartphoneServiceModel smartphoneServiceModel, Principal principal);

    SmartphoneServiceModel findByBrandAndModel(String brand, String model);

    SmartphoneServiceModel editSmartphone(String brand, String model, SmartphoneServiceModel smartphoneServiceModel, Principal principal);

    void deleteSmartphone(String brand, String model, Principal principal);

    List<SmartphoneServiceModel> findByBrand(String brand);

    void addSmartphoneToCart(HttpSession httpSession, String brand, String model);
}
