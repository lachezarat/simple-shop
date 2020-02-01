package com.myproject.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TabletsController extends BaseController {

    @GetMapping("/tablets")
    public ModelAndView tablets() {
        return super.view("tablets");
    }
}
