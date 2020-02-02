package com.myproject.eshop.services;

import com.myproject.eshop.data.entities.Laptop;

import java.util.List;

public interface LaptopService {

    List<Laptop> findAllLaptops();
}
