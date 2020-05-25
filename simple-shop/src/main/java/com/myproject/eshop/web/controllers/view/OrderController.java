package com.myproject.eshop.web.controllers.view;

import com.myproject.eshop.data.models.service.OrderServiceModel;
import com.myproject.eshop.services.OrderService;
import com.myproject.eshop.web.controllers.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController extends BaseController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/my-orders")
    public ModelAndView myOrders(Principal principal, ModelAndView modelAndView) {
        List<OrderServiceModel> orders =
                orderService.showUserOrders(principal.getName())
                        .stream()
                        .sorted((f, s) -> s.getCreated().compareTo(f.getCreated()))
                        .collect(Collectors.toList());
        modelAndView.addObject("orders", orders);

        return super.view(modelAndView, "user-orders");
    }

    @GetMapping("/my-orders/order/{orderId}")
    public ModelAndView singleOrder(@PathVariable String orderId, ModelAndView modelAndView, Principal principal) {
        OrderServiceModel order = orderService.showOrder(principal.getName(), orderId);
        modelAndView.addObject("order", order);

        return super.view(modelAndView, "single-order");
    }
}
