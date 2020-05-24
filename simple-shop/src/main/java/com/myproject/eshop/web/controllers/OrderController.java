package com.myproject.eshop.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myproject.eshop.data.entities.Order;
import com.myproject.eshop.data.models.binding.OrderCreateBindingModel;
import com.myproject.eshop.data.models.service.OrderServiceModel;
import com.myproject.eshop.services.OrderService;
import com.myproject.eshop.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PageTitle("Order")
    public ModelAndView order(@ModelAttribute("order") OrderCreateBindingModel order) {

        return super.view("/order");
    }

    @PostMapping
    public ModelAndView orderSave(@RequestBody String orderInfoJson) throws JsonProcessingException {
        OrderServiceModel orderServiceModel = orderService.saveOrder(orderInfoJson);

        return redirect("/");
    }
}
