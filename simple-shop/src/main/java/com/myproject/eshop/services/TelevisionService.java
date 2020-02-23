package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.TelevisionServiceModel;

import java.util.List;

public interface TelevisionService {

    List<TelevisionServiceModel> findAllTelevisions();

    TelevisionServiceModel createTelevision(TelevisionServiceModel televisionServiceModel);

    TelevisionServiceModel findByBrandAndModel(String brand, String model);

    TelevisionServiceModel editTelevision(String brand, String model, TelevisionServiceModel televisionServiceModel);

    void deleteTelevision(String brand, String model);
}
