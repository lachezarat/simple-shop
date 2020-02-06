package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.entities.Laptop;
import com.myproject.eshop.data.models.service.LaptopServiceModel;
import com.myproject.eshop.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LaptopsController extends BaseController {
    private final LaptopService laptopService;

    @Autowired
    public LaptopsController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping("/laptops-all")
    public ModelAndView laptops(ModelAndView modelAndView) {
        List<LaptopServiceModel> laptops = laptopService.findAllLaptops();
        modelAndView.addObject("laptops", laptops);
        return super.view(modelAndView, "/laptop/laptops-all");
    }
}
