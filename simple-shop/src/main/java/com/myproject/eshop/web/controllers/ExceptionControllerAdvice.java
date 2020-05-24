package com.myproject.eshop.web.controllers;

import com.myproject.eshop.error.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(LaptopNotFoundException.class)
    public ModelAndView handleNotFoundException(LaptopNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(SmartphoneNotFoundException.class)
    public ModelAndView handleNotFoundException(SmartphoneNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(SmartwatchNotFoundException.class)
    public ModelAndView handleNotFoundException(SmartwatchNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(TabletNotFoundException.class)
    public ModelAndView handleNotFoundException(TabletNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(InvalidItemException.class)
    public ModelAndView handleInvalidItemException(InvalidItemException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", e.getMessage());

        return modelAndView;
    }
}
