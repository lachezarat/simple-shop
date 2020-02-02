package com.myproject.eshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SmartphonesController extends BaseController {

    @GetMapping("/smartphones")
    public ModelAndView smartphones() {
        return super.view("smartphones");
    }
}
