package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.view.SmartphoneAllViewModel;
import com.myproject.eshop.services.SmartphoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SmartphonesController extends BaseController {

    private final SmartphoneService smartphoneService;
    private final ModelMapper modelMapper;

    @Autowired
    public SmartphonesController(SmartphoneService smartphoneService, ModelMapper modelMapper) {
        this.smartphoneService = smartphoneService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/smartphones-all")
    public ModelAndView smartphones(ModelAndView modelAndView) {
        List<SmartphoneAllViewModel> smartphones =
                smartphoneService.findAllSmartphones()
                        .stream()
                        .map(smartphone -> modelMapper.map(smartphone, SmartphoneAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("smartphones", smartphones);
        return super.view(modelAndView, "/smartphone/smartphones-all");
    }
}
