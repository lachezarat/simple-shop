package com.myproject.eshop.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    public ModelAndView view(String viewName) {
        return view(new ModelAndView(), viewName);
    }

    public ModelAndView view(ModelAndView modelAndView, String viewName) {
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    protected ModelAndView redirect(String url) {
        return view("redirect:" + url);
    }
}
