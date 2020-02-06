package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.entities.Smartwatch;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;
import com.myproject.eshop.services.SmartwatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SmartwatchesController extends BaseController {

    private final SmartwatchService smartwatchService;

    @Autowired
    public SmartwatchesController(SmartwatchService smartwatchService) {
        this.smartwatchService = smartwatchService;
    }

    @GetMapping("/smartwatches-all")
    public ModelAndView smartwatchesAndBands(ModelAndView modelAndView) {
        List<SmartwatchServiceModel> smartwatches = smartwatchService.findAllSmartwatches();
        modelAndView.addObject("smartwatches", smartwatches);
        return super.view(modelAndView, "/smartwatch/smartwatches-all");
    }
}
