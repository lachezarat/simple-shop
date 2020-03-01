package com.myproject.eshop.services.impl;

import com.myproject.eshop.data.entities.Log;
import com.myproject.eshop.data.models.service.LogServiceModel;
import com.myproject.eshop.repositories.LogRepository;
import com.myproject.eshop.services.LogService;
import org.modelmapper.ModelMapper;

public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LogServiceModel seedLogInDb(LogServiceModel logServiceModel) {
        Log log = modelMapper.map(logServiceModel, Log.class);
        return modelMapper.map(logRepository.saveAndFlush(log), LogServiceModel.class);
    }
}
