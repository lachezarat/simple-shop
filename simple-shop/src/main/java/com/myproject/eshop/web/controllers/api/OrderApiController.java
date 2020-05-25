package com.myproject.eshop.web.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myproject.eshop.data.models.binding.OrderCreateBindingModel;
import com.myproject.eshop.data.models.service.OrderServiceModel;
import com.myproject.eshop.services.OrderService;
import com.myproject.eshop.web.anotations.PageTitle;
import com.myproject.eshop.web.controllers.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/order")
public class OrderApiController extends BaseController {

    private final OrderService orderService;

    @Autowired
    public OrderApiController(OrderService orderService) {
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
