package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.entities.Tablet;
import com.myproject.eshop.services.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TabletsController extends BaseController {

    private final TabletService tabletService;

    @Autowired
    public TabletsController(TabletService tabletService) {
        this.tabletService = tabletService;
    }

    @GetMapping("/tablets-all")
    public ModelAndView tablets(ModelAndView modelAndView) {
        List<Tablet> tablets = tabletService.findAllTablets();
        modelAndView.addObject("tablets", tablets);
        return super.view(modelAndView, "/tablet/tablets-all");
    }
}
