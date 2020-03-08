package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.TabletCreateBindingModel;
import com.myproject.eshop.data.models.service.TabletServiceModel;
import com.myproject.eshop.data.models.view.TabletAllViewModel;
import com.myproject.eshop.services.TabletService;
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
public class TabletsController extends BaseController {

    private final TabletService tabletService;
    private final ModelMapper modelMapper;

    @Autowired
    public TabletsController(TabletService tabletService, ModelMapper modelMapper) {
        this.tabletService = tabletService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/tablets-all")
    @PageTitle(value = "All Tablets")
    public ModelAndView tablets(ModelAndView modelAndView) {
        List<TabletAllViewModel> tablets =
                tabletService.findAllTablets()
                        .stream()
                        .map(tablet -> modelMapper.map(tablet, TabletAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("tablets", tablets);
        return super.view(modelAndView, "/tablet/tablets-all");
    }

    @GetMapping("/admin/tablets-create")
    @PageTitle(value = "Create Tablet")
    public ModelAndView create() {
        return super.view("/tablet/tablets-create");
    }

    @PostMapping("/admin/tablets-create")
    public ModelAndView createConfirm(@ModelAttribute TabletCreateBindingModel model, Principal principal) {
        tabletService.createTablet(modelMapper.map(model, TabletServiceModel.class), principal);
        return super.redirect("/tablets-all");
    }

    @GetMapping("/tablets/{brand}/{model}")
    @PageTitle(value = "Show Tablet")
    public ModelAndView view(@PathVariable("brand") String brand, @PathVariable("model") String model, ModelAndView modelAndView) {
        TabletServiceModel tablet = tabletService.findByBrandAndModel(brand, model);
        modelAndView.addObject("tablet", tablet);
        return super.view(modelAndView, "/tablet/tablets-single-view");
    }

    @GetMapping("/admin/tablets-edit/{brand}/{model}")
    @PageTitle(value = "Edit Tablet")
    public ModelAndView edit(@PathVariable String brand, @PathVariable String model, ModelAndView modelAndView) {
        TabletServiceModel tablet = tabletService.findByBrandAndModel(brand, model);
        modelAndView.addObject("tablet", tablet);
        return super.view(modelAndView, "/tablet/tablets-edit");
    }

    @PostMapping("/admin/tablets-edit/{brand}/{model}")
    public ModelAndView confirmEdit(@PathVariable String brand, @PathVariable String model, @ModelAttribute TabletCreateBindingModel tablet, Principal principal) {
        tabletService.editTablet(brand, model, modelMapper.map(tablet, TabletServiceModel.class), principal);
        return super.redirect("/tablets/" + brand + "/" + model);
    }

    @PostMapping("/admin/tablets-delete/{brand}/{model}")
    public ModelAndView delete(@PathVariable String brand, @PathVariable String model, Principal principal) {
        tabletService.deleteTablet(brand, model, principal);
        return super.redirect("/tablets-all");
    }

    @GetMapping("/tablets-brand/{brand}")
    @PageTitle(value = "Tablets By Brand")
    public ModelAndView byBrand(@PathVariable String brand, ModelAndView modelAndView) {
        List<TabletAllViewModel> tablets =
                tabletService.findByBrand(brand)
                        .stream()
                        .map(tablet -> modelMapper.map(tablet, TabletAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("tablets", tablets);
        return super.view(modelAndView, "/tablet/tablets-all");
    }
}
