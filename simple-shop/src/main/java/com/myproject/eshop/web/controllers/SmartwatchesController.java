package com.myproject.eshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SmartwatchesController extends BaseController {

    @GetMapping("/smartwatches")
    public ModelAndView smartwathesAndBands() {
        return super.view("smartwatches");
    }
}
