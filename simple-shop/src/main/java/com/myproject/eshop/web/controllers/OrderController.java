package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.OrderCreateBindingModel;
import com.myproject.eshop.data.models.service.OrderServiceModel;
import com.myproject.eshop.services.OrderService;
import com.myproject.eshop.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class OrderController extends BaseController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/order")
    @PageTitle("Order")
    public ModelAndView order(@ModelAttribute("order") OrderCreateBindingModel order) {

        return super.view("/order");
    }

    @PostMapping("/order")
    public ModelAndView orderSave(@Valid @ModelAttribute("order") OrderCreateBindingModel order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("order");
        }

        orderService.saveOrder(modelMapper.map(order, OrderServiceModel.class));
        return super.view("success-order");
    }
}
