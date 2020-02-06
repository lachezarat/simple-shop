package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.TelevisionServiceModel;

import java.util.List;

public interface TelevisionService {

    List<TelevisionServiceModel> findAllTelevisions();
}
