package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Tablet;
import com.myproject.eshop.data.models.service.TabletServiceModel;
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
}
