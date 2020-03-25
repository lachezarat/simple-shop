package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.TabletServiceModel;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

public interface TabletService {

    List<TabletServiceModel> findAllTablets();

    TabletServiceModel createTablet(TabletServiceModel tabletServiceModel, Principal principal);

    TabletServiceModel findByBrandAndModel(String brand, String model);

    TabletServiceModel editTablet(String brand, String model, TabletServiceModel tabletServiceModel, Principal principal);

    void deleteTablet(String brand, String model, Principal principal);

    List<TabletServiceModel> findByBrand(String brand);

    void addTabletToCart(HttpSession httpSession, String brand, String model);
}
