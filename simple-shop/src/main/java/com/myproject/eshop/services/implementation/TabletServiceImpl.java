package com.myproject.eshop.services.implementation;

import com.myproject.eshop.data.entities.Tablet;
import com.myproject.eshop.repositories.TabletRepository;
import com.myproject.eshop.services.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabletServiceImpl implements TabletService {

    private final TabletRepository tabletRepository;

    @Autowired
    public TabletServiceImpl(TabletRepository tabletRepository) {
        this.tabletRepository = tabletRepository;
    }

    @Override
    public List<Tablet> findAllTablets() {
        return tabletRepository.findAll();
    }
}
