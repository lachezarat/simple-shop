package com.myproject.eshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TelevisionsController extends BaseController {

    @GetMapping("/televisions")
    public ModelAndView televisions() {
        return super.view("televisions");
    }
}
