package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Smartphone;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
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
}
