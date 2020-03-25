package com.myproject.eshop.services.impl;

import com.myproject.eshop.data.entities.Laptop;
import com.myproject.eshop.data.models.service.LaptopServiceModel;
import com.myproject.eshop.data.models.service.ProductServiceModel;
import com.myproject.eshop.error.LaptopNotFoundException;
import com.myproject.eshop.repositories.LaptopRepository;
import com.myproject.eshop.services.LaptopService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper, Logger logger) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public List<LaptopServiceModel> findAllLaptops() {
        return laptopRepository.findAll()
                .stream()
                .map(laptop -> modelMapper.map(laptop, LaptopServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public LaptopServiceModel createLaptop(LaptopServiceModel laptopServiceModel, Principal principal) {
        Laptop laptop = modelMapper.map(laptopServiceModel, Laptop.class);

        laptopRepository.saveAndFlush(laptop);

        logger.info(String.format("%s created laptop %s %s.",
                principal.getName(),
                laptop.getBrand(),
                laptop.getModel()));

        return modelMapper.map(laptop, LaptopServiceModel.class);
    }

    @Override
    public LaptopServiceModel findByBrandAndModel(String brand, String model) {
        return laptopRepository.findByBrandAndModel(brand, model)
                .map(laptop -> modelMapper.map(laptop, LaptopServiceModel.class))
                .orElseThrow(() -> new LaptopNotFoundException("No such laptop!"));
    }

    @Override
    public LaptopServiceModel editLaptop(String brand, String model, LaptopServiceModel laptopServiceModel, Principal principal) {
        Laptop laptop = laptopRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new LaptopNotFoundException("No such laptop"));

        laptop.setImgUrl(laptopServiceModel.getImgUrl());
        laptop.setDisplay(laptopServiceModel.getDisplay());
        laptop.setGraphicsProcessingUnit(laptopServiceModel.getGraphicsProcessingUnit());
        laptop.setCentralProcessingUnit(laptopServiceModel.getCentralProcessingUnit());
        laptop.setPrice(laptopServiceModel.getPrice());
        laptop.setRam(laptopServiceModel.getRam());
        laptop.setStorage(laptopServiceModel.getStorage());
        laptop.setWeight(laptopServiceModel.getWeight());

        laptopRepository.saveAndFlush(laptop);

        logger.info(String.format("%s edited laptop %s %s.",
                principal.getName(),
                brand,
                model));

        return modelMapper.map(laptop, LaptopServiceModel.class);
    }

    @Override
    public void deleteLaptop(String brand, String model, Principal principal) {
        Laptop laptop = laptopRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new LaptopNotFoundException("No such laptop!"));

        laptopRepository.delete(laptop);

        logger.info(String.format("%s deleted laptop %s %s.",
                principal.getName(),
                brand,
                model));
    }

    @Override
    public List<LaptopServiceModel> findByBrand(String brand) {
        return laptopRepository.findAllByBrand(brand)
                .stream()
                .map(laptop -> modelMapper.map(laptop, LaptopServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addLaptopToCart(HttpSession httpSession, String brand, String model) {
        LaptopServiceModel laptopServiceModel = laptopRepository.findByBrandAndModel(brand, model)
                .map(laptop -> modelMapper.map(laptop, LaptopServiceModel.class))
                .orElseThrow(() -> new LaptopNotFoundException("No such laptop!"));

        ProductServiceModel productServiceModel = modelMapper.map(laptopServiceModel, ProductServiceModel.class);

        List<ProductServiceModel> products = (List<ProductServiceModel>) httpSession.getAttribute("cartProducts");

        products.add(productServiceModel);
    }
}
