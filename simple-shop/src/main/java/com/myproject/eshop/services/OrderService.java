package com.myproject.eshop.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.myproject.eshop.data.models.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    OrderServiceModel saveOrder(String orderInfo) throws JsonProcessingException;

    List<OrderServiceModel> showUserOrders(String username);

    OrderServiceModel showOrder(String username, String orderId);
}
