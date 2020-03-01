package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.LaptopCreateBindingModel;
import com.myproject.eshop.data.models.service.LaptopServiceModel;
import com.myproject.eshop.data.models.view.LaptopAllViewModel;
import com.myproject.eshop.services.LaptopService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LaptopsController extends BaseController {

    private final LaptopService laptopService;
    private final ModelMapper modelMapper;

    @Autowired
    public LaptopsController(LaptopService laptopService, ModelMapper modelMapper) {
        this.laptopService = laptopService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/laptops-all")
    public ModelAndView laptops(ModelAndView modelAndView) {
        List<LaptopAllViewModel> laptops =
                laptopService.findAllLaptops()
                        .stream()
                        .map(laptop -> modelMapper.map(laptop, LaptopAllViewModel.class))
                        .collect(Collectors.toList());
        modelAndView.addObject("laptops", laptops);
        return super.view(modelAndView, "/laptop/laptops-all");
    }

    @GetMapping("/laptops-create")
    public ModelAndView create() {
        return super.view("/laptop/laptops-create");
    }

    @PostMapping("/laptops-create")
    public ModelAndView createConfirm(@ModelAttribute LaptopCreateBindingModel model, Principal principal) {
        laptopService.createLaptop(modelMapper.map(model, LaptopServiceModel.class), principal);
        return super.redirect("/laptops-all");
    }

    @GetMapping("/laptops/{brand}/{model}")
    public ModelAndView view(@PathVariable String brand, @PathVariable String model, ModelAndView modelAndView) {
        LaptopServiceModel laptop = laptopService.findByBrandAndModel(brand, model);
        modelAndView.addObject("laptop", laptop);
        return super.view(modelAndView, "/laptop/laptops-single-view");
    }

    @GetMapping("/laptops-edit/{brand}/{model}")
    public ModelAndView edit(@PathVariable String brand, @PathVariable String model, ModelAndView modelAndView) {
        LaptopServiceModel laptop = laptopService.findByBrandAndModel(brand, model);
        modelAndView.addObject("laptop", laptop);
        return super.view(modelAndView, "/laptop/laptops-edit");
    }

    @PostMapping("/laptops-edit/{brand}/{model}")
    public ModelAndView confirmEdit(@PathVariable String brand, @PathVariable String model, @ModelAttribute LaptopCreateBindingModel laptop, Principal principal) {
        laptopService.editLaptop(brand, model, modelMapper.map(laptop, LaptopServiceModel.class), principal);
        return super.redirect("/laptops/" + brand + "/" + model);
    }

    @PostMapping("/laptops-delete/{brand}/{model}")
    public ModelAndView delete(@PathVariable String brand, @PathVariable String model, Principal principal) {
        laptopService.deleteLaptop(brand, model, principal);

        return redirect("/laptops-all");
    }

    @GetMapping("/laptops-{brand}")
    public ModelAndView byBrand(@PathVariable String brand, ModelAndView modelAndView) {
        List<LaptopServiceModel> laptops = laptopService.allByBrand(brand);
        modelAndView.addObject("laptops", laptops);
        return super.view(modelAndView, "/laptop/laptops-all");
    }
}
