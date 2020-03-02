package com.myproject.eshop.web.controllers;

import com.myproject.eshop.web.anotations.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteInfoController extends BaseController {

    @GetMapping("/contacts")
    @PageTitle(value = "Contacts")
    public ModelAndView contacts() {
        return super.view("contacts");
    }

    @GetMapping("/general-questions")
    @PageTitle(value = "FAQ's")
    public ModelAndView generalQuestions() {
        return super.view("general-questions");
    }
}
