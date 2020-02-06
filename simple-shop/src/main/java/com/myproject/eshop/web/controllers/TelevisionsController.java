package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.view.TelevisionAllViewModel;
import com.myproject.eshop.services.TelevisionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TelevisionsController extends BaseController {

    private final TelevisionService televisionService;
    private final ModelMapper modelMapper;


    @Autowired
    public TelevisionsController(TelevisionService televisionService, ModelMapper modelMapper) {
        this.televisionService = televisionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/televisions-all")
    public ModelAndView televisions(ModelAndView modelAndView) {
        List<TelevisionAllViewModel> televisions =
                televisionService.findAllTelevisions()
                        .stream()
                        .map(television -> modelMapper.map(television, TelevisionAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("televisions", televisions);
        return super.view(modelAndView, "/television/televisions-all");
    }
}
