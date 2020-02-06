package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.SmartphoneServiceModel;

import java.util.List;

public interface SmartphoneService {

    List<SmartphoneServiceModel> findAllSmartphones();
}
