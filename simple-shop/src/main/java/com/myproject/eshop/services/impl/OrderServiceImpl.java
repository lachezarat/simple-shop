package com.myproject.eshop.services.impl;

import com.myproject.eshop.data.entities.Order;
import com.myproject.eshop.data.models.service.OrderServiceModel;
import com.myproject.eshop.repositories.OrderRepository;
import com.myproject.eshop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderServiceModel saveOrder(OrderServiceModel orderServiceModel) {
        orderServiceModel.setDate(new Date());

        Order order = modelMapper.map(orderServiceModel, Order.class);

        return modelMapper.map(orderRepository.saveAndFlush(order), OrderServiceModel.class);
    }
}
