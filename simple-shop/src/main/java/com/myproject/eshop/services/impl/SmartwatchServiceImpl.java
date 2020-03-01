package com.myproject.eshop.services.impl;

import com.myproject.eshop.data.entities.Smartwatch;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;
import com.myproject.eshop.error.SmartwatchNotFoundException;
import com.myproject.eshop.repositories.SmartwatchRepository;
import com.myproject.eshop.services.SmartwatchService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartwatchServiceImpl implements SmartwatchService {

    private final SmartwatchRepository smartwatchRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public SmartwatchServiceImpl(SmartwatchRepository smartwatchRepository, ModelMapper modelMapper, Logger logger) {
        this.smartwatchRepository = smartwatchRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public List<SmartwatchServiceModel> findAllSmartwatches() {
        return smartwatchRepository.findAll()
                .stream()
                .map(smartwatch -> modelMapper.map(smartwatch, SmartwatchServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SmartwatchServiceModel createSmartwatch(SmartwatchServiceModel smartwatchServiceModel, Principal principal) {
        Smartwatch smartwatch = modelMapper.map(smartwatchServiceModel, Smartwatch.class);

        smartwatchRepository.saveAndFlush(smartwatch);

        logger.info(String.format("%s created smartwatch %s %s.",
                principal.getName(),
                smartwatch.getBrand(),
                smartwatch.getModel()));

        return modelMapper.map(smartwatch, SmartwatchServiceModel.class);
    }

    @Override
    public SmartwatchServiceModel findByBrandAndModel(String brand, String model) {
        return smartwatchRepository.findByBrandAndModel(brand, model)
                .map(smartwatch -> modelMapper.map(smartwatch, SmartwatchServiceModel.class))
                .orElseThrow(() -> new SmartwatchNotFoundException("No such smartwatch!"));
    }

    @Override
    public SmartwatchServiceModel editSmartwatch(String brand, String model, SmartwatchServiceModel smartwatchServiceModel, Principal principal) {
        Smartwatch smartwatch = smartwatchRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new SmartwatchNotFoundException("No such smartwatch!"));

        smartwatch.setImgUrl(smartwatchServiceModel.getImgUrl());
        smartwatch.setDisplay(smartwatchServiceModel.getDisplay());
        smartwatch.setPrice(smartwatchServiceModel.getPrice());
        smartwatch.setCentralProcessingUnit(smartwatchServiceModel.getCentralProcessingUnit());
        smartwatch.setStorage(smartwatchServiceModel.getStorage());
        smartwatch.setRam(smartwatchServiceModel.getRam());
        smartwatch.setHasCamera(smartwatchServiceModel.isHasCamera());
        smartwatch.setBatteryCapacity(smartwatchServiceModel.getBatteryCapacity());

        smartwatchRepository.saveAndFlush(smartwatch);

        logger.info(String.format("%s edited smartwatch %s %s.",
                principal.getName(),
                brand,
                model));

        return modelMapper.map(smartwatch, SmartwatchServiceModel.class);
    }

    @Override
    public void deleteSmartwatch(String brand, String model, Principal principal) {
        Smartwatch smartwatch = smartwatchRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new SmartwatchNotFoundException("No such smartwatch!"));

        smartwatchRepository.delete(smartwatch);

        logger.info(String.format("%s deleted smartwatch %s %s.",
                principal.getName(),
                brand,
                model));
    }
}
