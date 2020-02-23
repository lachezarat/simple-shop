package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.TabletServiceModel;

import java.util.List;

public interface TabletService {

    List<TabletServiceModel> findAllTablets();

    TabletServiceModel createTablet(TabletServiceModel tabletServiceModel);

    TabletServiceModel findByBrandAndModel(String brand, String model);

    TabletServiceModel editTablet(String brand, String model, TabletServiceModel tabletServiceModel);
}
