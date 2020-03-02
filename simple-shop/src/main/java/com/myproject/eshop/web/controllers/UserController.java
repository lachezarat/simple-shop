package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.UserRegisterBindingModel;
import com.myproject.eshop.data.models.service.UserServiceModel;
import com.myproject.eshop.services.UserService;
import com.myproject.eshop.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    @PageTitle(value = "Login")
    public ModelAndView login() {
        return super.view("login");
    }

    @GetMapping("/register")
    @PageTitle(value = "Register")
    public ModelAndView register() {
        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/login");
    }
}
