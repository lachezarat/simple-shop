package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Laptop;
import com.myproject.eshop.data.models.service.LaptopServiceModel;
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
}
