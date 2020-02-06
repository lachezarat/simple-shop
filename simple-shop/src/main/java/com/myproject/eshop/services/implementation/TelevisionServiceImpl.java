package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.models.service.TelevisionServiceModel;
import com.myproject.eshop.repositories.TelevisionRepository;
import com.myproject.eshop.services.TelevisionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionServiceImpl implements TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TelevisionServiceImpl(TelevisionRepository televisionRepository, ModelMapper modelMapper) {
        this.televisionRepository = televisionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TelevisionServiceModel> findAllTelevisions() {
        return televisionRepository.findAll()
                .stream()
                .map(television -> modelMapper.map(television, TelevisionServiceModel.class))
                .collect(Collectors.toList());
    }
}
