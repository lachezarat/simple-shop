package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Smartphone;
import com.myproject.eshop.repositories.SmartphoneRepository;
import com.myproject.eshop.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;

    @Autowired
    public SmartphoneServiceImpl(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    @Override
    public List<Smartphone> findAllSmartphones() {
        return smartphoneRepository.findAll();
    }
}
