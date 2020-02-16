package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.SmartwatchCreateBindingModel;
import com.myproject.eshop.data.models.service.SmartwatchServiceModel;
import com.myproject.eshop.data.models.view.SmartphoneAllViewModel;
import com.myproject.eshop.data.models.view.SmartwatchAllViewModel;
import com.myproject.eshop.services.SmartwatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SmartwatchesController extends BaseController {

    private final SmartwatchService smartwatchService;
    private final ModelMapper modelMapper;
    ;

    @Autowired
    public SmartwatchesController(SmartwatchService smartwatchService, ModelMapper modelMapper) {
        this.smartwatchService = smartwatchService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/smartwatches-all")
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
    public ModelAndView create() {
        return super.view("smartwatch/smartwatches-create");
    }

    @PostMapping("/smartwatches-create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createConfirm(@ModelAttribute SmartwatchCreateBindingModel model) {
        smartwatchService.createSmartwatch(modelMapper.map(model, SmartwatchServiceModel.class));
        return super.redirect("smartwatches-all");
    }
}
