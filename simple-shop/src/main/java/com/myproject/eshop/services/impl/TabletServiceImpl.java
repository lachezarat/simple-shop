package com.myproject.eshop.services.impl;

import com.myproject.eshop.data.entities.Tablet;
import com.myproject.eshop.data.models.service.TabletServiceModel;
import com.myproject.eshop.error.TabletNotFoundException;
import com.myproject.eshop.repositories.TabletRepository;
import com.myproject.eshop.services.TabletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabletServiceImpl implements TabletService {

    private final TabletRepository tabletRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TabletServiceImpl(TabletRepository tabletRepository, ModelMapper modelMapper) {
        this.tabletRepository = tabletRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TabletServiceModel> findAllTablets() {
        return tabletRepository.findAll()
                .stream()
                .map(tablet -> modelMapper.map(tablet, TabletServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TabletServiceModel createTablet(TabletServiceModel tabletServiceModel) {
        Tablet tablet = modelMapper.map(tabletServiceModel, Tablet.class);
        return modelMapper.map(tabletRepository.saveAndFlush(tablet), TabletServiceModel.class);
    }

    @Override
    public TabletServiceModel findByBrandAndModel(String brand, String model) {
        return tabletRepository.findByBrandAndModel(brand, model)
                .map(tablet -> modelMapper.map(tablet, TabletServiceModel.class))
                .orElseThrow(() -> new TabletNotFoundException("No such tablet!"));
    }

    @Override
    public TabletServiceModel editTablet(String brand, String model, TabletServiceModel tabletServiceModel) {
        Tablet tablet = tabletRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new TabletNotFoundException("No such tablet!"));

        tablet.setImgUrl(tabletServiceModel.getImgUrl());
        tablet.setDisplay(tabletServiceModel.getDisplay());
        tablet.setCentralProcessingUnit(tabletServiceModel.getCentralProcessingUnit());
        tablet.setPrice(tabletServiceModel.getPrice());
        tablet.setRam(tabletServiceModel.getRam());
        tablet.setStorage(tabletServiceModel.getStorage());
        tablet.setCamera(tabletServiceModel.getCamera());

        return modelMapper.map(tabletRepository.saveAndFlush(tablet), TabletServiceModel.class);
    }

    @Override
    public void deleteTablet(String brand, String model) {
        Tablet tablet = tabletRepository.findByBrandAndModel(brand, model)
                .orElseThrow(() -> new TabletNotFoundException("No such tablet!"));

        tabletRepository.delete(tablet);
    }
}
