package com.myproject.eshop.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.myproject.eshop.data.models.service.OrderServiceModel;

public interface OrderService {

    OrderServiceModel saveOrder(String orderInfo) throws JsonProcessingException;

}
