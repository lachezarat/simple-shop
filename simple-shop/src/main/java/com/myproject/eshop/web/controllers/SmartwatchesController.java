package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.SmartwatchCreateBindingModel;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;
import com.myproject.eshop.data.models.view.SmartwatchAllViewModel;
import com.myproject.eshop.services.SmartwatchService;
import com.myproject.eshop.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SmartwatchesController extends BaseController {

    private final SmartwatchService smartwatchService;
    private final ModelMapper modelMapper;

    @Autowired
    public SmartwatchesController(SmartwatchService smartwatchService, ModelMapper modelMapper) {
        this.smartwatchService = smartwatchService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/smartwatches-all")
    @PageTitle(value = "All Smartwatches")
    public ModelAndView smartwatches(ModelAndView modelAndView) {
        List<SmartwatchAllViewModel> smartwatches =
                smartwatchService.findAllSmartwatches()
                        .stream()
                        .map(smartwatch -> modelMapper.map(smartwatch, SmartwatchAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("smartwatches", smartwatches);
        return super.view(modelAndView, "/smartwatch/smartwatches-all");
    }

    @GetMapping("/smartwatches-create")
    @PageTitle(value = "Create Smartwatch")
    public ModelAndView create() {
        return super.view("smartwatch/smartwatches-create");
    }

    @PostMapping("/smartwatches-create")
    public ModelAndView createConfirm(@ModelAttribute SmartwatchCreateBindingModel model, Principal principal) {
        smartwatchService.createSmartwatch(modelMapper.map(model, SmartwatchServiceModel.class), principal);
        return super.redirect("smartwatches-all");
    }

    @GetMapping("/smartwatches/{brand}/{model}")
    @PageTitle(value = "Show Smartwatch")
    public ModelAndView view(@PathVariable("brand") String brand, @PathVariable("model") String model, ModelAndView modelAndView) {
        SmartwatchServiceModel smartwatch = smartwatchService.findByBrandAndModel(brand, model);
        modelAndView.addObject("smartwatch", smartwatch);
        return super.view(modelAndView, "/smartwatch/smartwatches-single-view");
    }

    @GetMapping("/smartwatches-edit/{brand}/{model}")
    @PageTitle(value = "Edit Smartwatch")
    public ModelAndView edit(@PathVariable String brand, @PathVariable String model, ModelAndView modelAndView) {
        SmartwatchServiceModel smartwatch = smartwatchService.findByBrandAndModel(brand, model);
        modelAndView.addObject("smartwatch", smartwatch);
        return super.view(modelAndView, "/smartwatch/smartwatches-edit");
    }

    @PostMapping("/smartwatches-edit/{brand}/{model}")
    public ModelAndView confirmEdit(@PathVariable String brand, @PathVariable String model, @ModelAttribute SmartwatchCreateBindingModel smartwatch, Principal principal) {
        smartwatchService.editSmartwatch(brand, model, modelMapper.map(smartwatch, SmartwatchServiceModel.class), principal);
        return super.redirect("/smartwatches/" + brand + "/" + model);
    }

    @PostMapping("/smartwatches-delete/{brand}/{model}")
    public ModelAndView delete(@PathVariable String brand, @PathVariable String model, Principal principal) {
        smartwatchService.deleteSmartwatch(brand, model, principal);
        return super.redirect("/smartwatches-all");
    }
}
