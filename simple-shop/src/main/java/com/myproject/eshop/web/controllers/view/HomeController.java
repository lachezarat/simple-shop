package com.myproject.eshop.web.controllers.view;

import com.myproject.eshop.web.anotations.PageTitle;
import com.myproject.eshop.web.controllers.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    @PageTitle(value = "Welcome")
    public ModelAndView index() {
        return super.view("index");
    }
}
