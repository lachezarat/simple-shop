package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.entities.Smartphone;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
import com.myproject.eshop.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SmartphonesController extends BaseController {

    private final SmartphoneService smartphoneService;

    @Autowired
    public SmartphonesController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @GetMapping("/smartphones-all")
    public ModelAndView smartphones(ModelAndView modelAndView) {
        List<SmartphoneServiceModel> smartphones = smartphoneService.findAllSmartphones();
        modelAndView.addObject("smartphones", smartphones);
        return super.view(modelAndView, "/smartphone/smartphones-all");
    }
}
