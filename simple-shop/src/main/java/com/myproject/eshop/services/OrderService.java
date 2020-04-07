package com.myproject.eshop.services;


import com.myproject.eshop.data.models.service.OrderServiceModel;

public interface OrderService {

    OrderServiceModel saveOrder(OrderServiceModel orderServiceModel);
}
