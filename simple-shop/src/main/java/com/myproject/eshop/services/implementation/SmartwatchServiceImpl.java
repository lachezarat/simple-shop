package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Smartwatch;
import com.myproject.eshop.repositories.SmartwatchRepository;
import com.myproject.eshop.services.SmartwatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartwatchServiceImpl implements SmartwatchService {

    private final SmartwatchRepository smartwatchRepository;

    @Autowired
    public SmartwatchServiceImpl(SmartwatchRepository smartwatchRepository) {
        this.smartwatchRepository = smartwatchRepository;
    }

    @Override
    public List<Smartwatch> findAllSmartwatches() {
        return smartwatchRepository.findAll();
    }
}
