package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Laptop;
import com.myproject.eshop.data.models.service.LaptopServiceModel;
import com.myproject.eshop.error.LaptopNotFoundException;
import com.myproject.eshop.repositories.LaptopRepository;
import com.myproject.eshop.services.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LaptopServiceModel> findAllLaptops() {
        return laptopRepository.findAll()
                .stream()
                .map(laptop -> modelMapper.map(laptop, LaptopServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public LaptopServiceModel createLaptop(LaptopServiceModel laptopServiceModel) {
        Laptop laptop = modelMapper.map(laptopServiceModel, Laptop.class);
        return modelMapper.map(laptopRepository.saveAndFlush(laptop), LaptopServiceModel.class);
    }

    @Override
    public LaptopServiceModel findByBrandAndModel(String brand, String model) {
        return laptopRepository.findByBrandAndModel(brand, model)
                .map(laptop -> modelMapper.map(laptop, LaptopServiceModel.class))
                .orElseThrow(() -> new LaptopNotFoundException("No such laptop!"));
    }

    @Override
    public LaptopServiceModel editLaptop(String brand, String model, LaptopServiceModel laptopServiceModel) {
        Laptop laptop = laptopRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new LaptopNotFoundException("No such laptop"));

        laptop.setDisplay(laptopServiceModel.getDisplay());
        laptop.setCentralProcessingUnit(laptopServiceModel.getCentralProcessingUnit());
        laptop.setGraphicsProcessingUnit(laptopServiceModel.getGraphicsProcessingUnit());
        laptop.setPrice(laptopServiceModel.getPrice());
        laptop.setRam(laptopServiceModel.getRam());
        laptop.setStorage(laptopServiceModel.getStorage());
        laptop.setWeight(laptopServiceModel.getWeight());

        return modelMapper.map(laptopRepository.saveAndFlush(laptop), LaptopServiceModel.class);
    }
}
