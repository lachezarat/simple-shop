package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.TelevisionCreateBindingModel;
import com.myproject.eshop.data.models.service.TelevisionServiceModel;
import com.myproject.eshop.data.models.view.TelevisionAllViewModel;
import com.myproject.eshop.services.TelevisionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/televisions-create")
    public ModelAndView create() {
        return super.view("television/televisions-create");
    }

    @PostMapping("/televisions-create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createConfirm(@ModelAttribute TelevisionCreateBindingModel model) {
        televisionService.createTelevision(modelMapper.map(model, TelevisionServiceModel.class));
        return super.redirect("/televisions-all");
    }

    @GetMapping("/televisions/{brand}/{model}")
    public ModelAndView view(@PathVariable("brand") String brand, @PathVariable("model") String model, ModelAndView modelAndView) {
        TelevisionServiceModel television = televisionService.findByBrandAndModel(brand, model);
        modelAndView.addObject("television", television);
        return super.view(modelAndView, "/television/televisions-single-view");
    }

    @GetMapping("/televisions-edit/{brand}/{model}")
    public ModelAndView edit(@PathVariable String brand, @PathVariable String model, ModelAndView modelAndView) {
        TelevisionServiceModel television = televisionService.findByBrandAndModel(brand, model);
        modelAndView.addObject("television", television);
        return super.view(modelAndView, "/television/televisions-edit");
    }

    @PostMapping("/televisions-edit/{brand}/{model}")
    public ModelAndView confirmEdit(@PathVariable String brand, @PathVariable String model, @ModelAttribute TelevisionCreateBindingModel television) {
        televisionService.editTelevision(brand, model, modelMapper.map(television, TelevisionServiceModel.class));
        return super.redirect("/televisions/" + brand + "/" + model);
    }
}
