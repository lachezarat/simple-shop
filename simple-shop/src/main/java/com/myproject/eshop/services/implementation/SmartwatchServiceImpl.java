package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.models.service.SmartwatchServiceModel;
import com.myproject.eshop.repositories.SmartwatchRepository;
import com.myproject.eshop.services.SmartwatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartwatchServiceImpl implements SmartwatchService {

    private final SmartwatchRepository smartwatchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SmartwatchServiceImpl(SmartwatchRepository smartwatchRepository, ModelMapper modelMapper) {
        this.smartwatchRepository = smartwatchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SmartwatchServiceModel> findAllSmartwatches() {
        return smartwatchRepository.findAll()
                .stream()
                .map(smartwatch -> modelMapper.map(smartwatch, SmartwatchServiceModel.class))
                .collect(Collectors.toList());
    }
}
