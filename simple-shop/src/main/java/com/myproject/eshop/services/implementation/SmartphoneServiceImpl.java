package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Smartphone;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;
import com.myproject.eshop.error.SmartphoneNotFoundException;
import com.myproject.eshop.repositories.SmartphoneRepository;
import com.myproject.eshop.services.SmartphoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SmartphoneServiceImpl(SmartphoneRepository smartphoneRepository, ModelMapper modelMapper) {
        this.smartphoneRepository = smartphoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SmartphoneServiceModel> findAllSmartphones() {
        return smartphoneRepository.findAll()
                .stream()
                .map(smartphone -> modelMapper.map(smartphone, SmartphoneServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SmartphoneServiceModel createSmartphone(SmartphoneServiceModel smartphoneServiceModel) {
        Smartphone smartphone = modelMapper.map(smartphoneServiceModel, Smartphone.class);
        return modelMapper.map(smartphoneRepository.saveAndFlush(smartphone), SmartphoneServiceModel.class);
    }

    @Override
    public SmartphoneServiceModel findByBrandAndModel(String brand, String model) {
        return smartphoneRepository.findByBrandAndModel(brand, model)
                .map(smartphone -> modelMapper.map(smartphone, SmartphoneServiceModel.class))
                .orElseThrow(() -> new SmartphoneNotFoundException("No such smartphone!"));
    }

    @Override
    public SmartphoneServiceModel editSmartphone(String brand, String model, SmartphoneServiceModel smartphoneServiceModel) {
        Smartphone smartphone = smartphoneRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new SmartphoneNotFoundException("No such smartphone!"));

        smartphone.setDisplay(smartphoneServiceModel.getDisplay());
        smartphone.setCentralProcessingUnit(smartphoneServiceModel.getCentralProcessingUnit());
        smartphone.setPrice(smartphoneServiceModel.getPrice());
        smartphone.setCamera(smartphone.getCamera());
        smartphone.setRam(smartphoneServiceModel.getRam());
        smartphone.setStorage(smartphoneServiceModel.getStorage());
        smartphone.setBatteryCapacity(smartphoneServiceModel.getBatteryCapacity());
        smartphone.setWeight(smartphoneServiceModel.getWeight());
        smartphone.setHasMemoryCardSlot(smartphoneServiceModel.isHasMemoryCardSlot());

        return modelMapper.map(smartphoneRepository.saveAndFlush(smartphone), SmartphoneServiceModel.class);
    }
}
