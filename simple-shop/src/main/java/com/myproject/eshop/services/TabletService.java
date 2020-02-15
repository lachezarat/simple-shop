package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.TabletServiceModel;

import java.util.List;

public interface TabletService {

    List<TabletServiceModel> findAllTablets();

    TabletServiceModel createTablet(TabletServiceModel tabletServiceModel);
}
