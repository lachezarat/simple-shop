package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.SmartphoneCreateBindingModel;
import com.myproject.eshop.data.models.service.SmartphoneServiceModel;
import com.myproject.eshop.data.models.view.SmartphoneAllViewModel;
import com.myproject.eshop.error.SmartphoneNotFoundException;
import com.myproject.eshop.services.SmartphoneService;
import com.myproject.eshop.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
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
    @PageTitle(value = "All Smartphones")
    public ModelAndView smartphones(ModelAndView modelAndView) {
        List<SmartphoneAllViewModel> smartphones =
                smartphoneService.findAllSmartphones()
                        .stream()
                        .map(smartphone -> modelMapper.map(smartphone, SmartphoneAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("smartphones", smartphones);
        return super.view(modelAndView, "/smartphone/smartphones-all");
    }

    @GetMapping("/admin/smartphones-create")
    @PageTitle(value = "Create Smartphone")
    public ModelAndView create() {
        return super.view("/smartphone/smartphones-create");
    }

    @PostMapping("/admin/smartphones-create")
    public ModelAndView createConfirm(@ModelAttribute SmartphoneCreateBindingModel model, Principal principal) {
        smartphoneService.createSmartphone(modelMapper.map(model, SmartphoneServiceModel.class), principal);
        return super.redirect("/smartphones-all");
    }

    @GetMapping("/smartphones/{brand}/{model}")
    @PageTitle(value = "Show Smartphone")
    public ModelAndView view(@PathVariable("brand") String brand, @PathVariable("model") String model, ModelAndView modelAndView) {
        SmartphoneServiceModel smartphone = smartphoneService.findByBrandAndModel(brand, model);
        modelAndView.addObject("smartphone", smartphone);
        return super.view(modelAndView, "/smartphone/smartphones-single-view");
    }

    @GetMapping("/admin/smartphones-edit/{brand}/{model}")
    @PageTitle(value = "Edit Smartphone")
    public ModelAndView edit(@PathVariable String brand, @PathVariable String model, ModelAndView modelAndView) {
        SmartphoneServiceModel smartphone = smartphoneService.findByBrandAndModel(brand, model);
        modelAndView.addObject("smartphone", smartphone);
        return super.view(modelAndView, "/smartphone/smartphones-edit");
    }

    @PostMapping("/admin/smartphones-edit/{brand}/{model}")
    public ModelAndView editConfirm(@PathVariable String brand, @PathVariable String model, @ModelAttribute SmartphoneCreateBindingModel smartphone, Principal principal) {
        smartphoneService.editSmartphone(brand, model, modelMapper.map(smartphone, SmartphoneServiceModel.class), principal);
        return super.redirect("/smartphones/" + brand + "/" + model);
    }

    @PostMapping("/admin/smartphones-delete/{brand}/{model}")
    public ModelAndView delete(@PathVariable String brand, @PathVariable String model, Principal principal) {
        smartphoneService.deleteSmartphone(brand, model, principal);
        return super.redirect("/smartphones-all");
    }

    @GetMapping("/smartphones-brand/{brand}")
    @PageTitle(value = "Smartphones By Brand")
    public ModelAndView byBrand(@PathVariable String brand, ModelAndView modelAndView) {
        List<SmartphoneAllViewModel> smartphones =
                smartphoneService.findByBrand(brand)
                        .stream()
                        .map(smartphone -> modelMapper.map(smartphone, SmartphoneAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("smartphones", smartphones);
        return super.view(modelAndView, "/smartphone/smartphones-all");
    }

    @ExceptionHandler(SmartphoneNotFoundException.class)
    public ModelAndView handleNotFoundException(SmartphoneNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }
}
