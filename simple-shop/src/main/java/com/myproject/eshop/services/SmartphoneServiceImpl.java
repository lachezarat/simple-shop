package com.myproject.eshop.services;

import com.myproject.eshop.data.entities.Smartphone;
import com.myproject.eshop.data.models.service.ProductServiceModel;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
import com.myproject.eshop.error.SmartphoneNotFoundException;
import com.myproject.eshop.repositories.SmartphoneRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public SmartphoneServiceImpl(SmartphoneRepository smartphoneRepository, ModelMapper modelMapper, Logger logger) {
        this.smartphoneRepository = smartphoneRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public List<SmartphoneServiceModel> findAllSmartphones() {
        return smartphoneRepository.findAll()
                .stream()
                .map(smartphone -> modelMapper.map(smartphone, SmartphoneServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SmartphoneServiceModel createSmartphone(SmartphoneServiceModel smartphoneServiceModel, Principal principal) {
        Smartphone smartphone = modelMapper.map(smartphoneServiceModel, Smartphone.class);

        smartphoneRepository.saveAndFlush(smartphone);

        logger.info(String.format("%s created smartphone %s %s.",
                principal.getName(),
                smartphone.getBrand(),
                smartphone.getModel()));

        return modelMapper.map(smartphone, SmartphoneServiceModel.class);
    }

    @Override
    public SmartphoneServiceModel findByBrandAndModel(String brand, String model) {
        return smartphoneRepository.findByBrandAndModel(brand, model)
                .map(smartphone -> modelMapper.map(smartphone, SmartphoneServiceModel.class))
                .orElseThrow(() -> new SmartphoneNotFoundException("No such smartphone!"));
    }

    @Override
    public SmartphoneServiceModel editSmartphone(String brand, String model, SmartphoneServiceModel smartphoneServiceModel, Principal principal) {
        Smartphone smartphone = smartphoneRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new SmartphoneNotFoundException("No such smartphone!"));

        smartphone.setImgUrl(smartphoneServiceModel.getImgUrl());
        smartphone.setDisplay(smartphoneServiceModel.getDisplay());
        smartphone.setCentralProcessingUnit(smartphoneServiceModel.getCentralProcessingUnit());
        smartphone.setPrice(smartphoneServiceModel.getPrice());
        smartphone.setCamera(smartphone.getCamera());
        smartphone.setRam(smartphoneServiceModel.getRam());
        smartphone.setStorage(smartphoneServiceModel.getStorage());
        smartphone.setBatteryCapacity(smartphoneServiceModel.getBatteryCapacity());
        smartphone.setWeight(smartphoneServiceModel.getWeight());
        smartphone.setHasMemoryCardSlot(smartphoneServiceModel.isHasMemoryCardSlot());

        smartphoneRepository.saveAndFlush(smartphone);

        logger.info(String.format("%s edited smartphone %s %s.",
                principal.getName(),
                brand,
                model));

        return modelMapper.map(smartphone, SmartphoneServiceModel.class);
    }

    @Override
    public void deleteSmartphone(String brand, String model, Principal principal) {
        Smartphone smartphone = smartphoneRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new SmartphoneNotFoundException("No such smartphone!"));

        smartphoneRepository.delete(smartphone);

        logger.info(String.format("%s deleted smartphone %s %s.",
                principal.getName(),
                brand,
                model));
    }

    @Override
    public List<SmartphoneServiceModel> findByBrand(String brand) {
        return smartphoneRepository.findAllByBrand(brand)
                .stream()
                .map(smartphone -> modelMapper.map(smartphone, SmartphoneServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addSmartphoneToCart(HttpSession httpSession, String brand, String model) {
        SmartphoneServiceModel smartphoneServiceModel = smartphoneRepository.findByBrandAndModel(brand, model)
                .map(smartphone -> modelMapper.map(smartphone, SmartphoneServiceModel.class))
                .orElseThrow(() -> new SmartphoneNotFoundException("No such smartphone!"));

        ProductServiceModel productServiceModel = modelMapper.map(smartphoneServiceModel, ProductServiceModel.class);

        List<ProductServiceModel> products = (List<ProductServiceModel>) httpSession.getAttribute("shoppingCart");

        products.add(productServiceModel);
    }
}
