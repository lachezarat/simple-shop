package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.TabletCreateBindingModel;
import com.myproject.eshop.data.models.service.TabletServiceModel;
import com.myproject.eshop.data.models.view.TabletAllViewModel;
import com.myproject.eshop.services.TabletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TabletsController extends BaseController {

    private final TabletService tabletService;
    private final ModelMapper modelMapper;

    @Autowired
    public TabletsController(TabletService tabletService, ModelMapper modelMapper) {
        this.tabletService = tabletService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/tablets-all")
    public ModelAndView tablets(ModelAndView modelAndView) {
        List<TabletAllViewModel> tablets =
                tabletService.findAllTablets()
                        .stream()
                        .map(tablet -> modelMapper.map(tablet, TabletAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("tablets", tablets);
        return super.view(modelAndView, "/tablet/tablets-all");
    }

    @GetMapping("/tablets-create")
    public ModelAndView create() {
        return super.view("tablets-create");
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createConfirm(@ModelAttribute TabletCreateBindingModel model) {
        tabletService.createTablet(modelMapper.map(model, TabletServiceModel.class));
        return super.redirect("/tablets-all");
    }
}
