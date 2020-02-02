package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Television;
import com.myproject.eshop.repositories.TelevisionRepository;
import com.myproject.eshop.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionServiceImpl implements TelevisionService {

    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionServiceImpl(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @Override
    public List<Television> findAllTelevisions() {
        return televisionRepository.findAll();
    }
}
