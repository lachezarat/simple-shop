package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Laptop;
import com.myproject.eshop.repositories.LaptopRepository;
import com.myproject.eshop.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public List<Laptop> findAllLaptops() {
        return laptopRepository.findAll();
    }
}
