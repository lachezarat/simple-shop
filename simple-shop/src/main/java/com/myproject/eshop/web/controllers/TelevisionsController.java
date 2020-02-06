package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.entities.Television;
import com.myproject.eshop.data.models.service.TelevisionServiceModel;
import com.myproject.eshop.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TelevisionsController extends BaseController {

    private final TelevisionService televisionService;

    @Autowired
    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping("/televisions-all")
    public ModelAndView televisions(ModelAndView modelAndView) {
        List<TelevisionServiceModel> televisions = televisionService.findAllTelevisions();
        modelAndView.addObject("televisions", televisions);
        return super.view(modelAndView, "/television/televisions-all");
    }
}
