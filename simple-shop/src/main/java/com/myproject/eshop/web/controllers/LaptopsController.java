package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.view.LaptopAllViewModel;
import com.myproject.eshop.services.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LaptopsController extends BaseController {
    private final LaptopService laptopService;
    private final ModelMapper modelMapper;

    @Autowired
    public LaptopsController(LaptopService laptopService, ModelMapper modelMapper) {
        this.laptopService = laptopService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/laptops-all")
    public ModelAndView laptops(ModelAndView modelAndView) {
        List<LaptopAllViewModel> laptops =
                laptopService.findAllLaptops()
                        .stream()
                        .map(laptop -> modelMapper.map(laptop, LaptopAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("laptops", laptops);
        return super.view(modelAndView, "/laptop/laptops-all");
    }
}
