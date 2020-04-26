package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.models.binding.UserRegisterBindingModel;
import com.myproject.eshop.data.models.service.UserServiceModel;
import com.myproject.eshop.services.UserService;
import com.myproject.eshop.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
    @PreAuthorize("isAnonymous()")
    @PageTitle(value = "Login")
    public ModelAndView login() {
        return super.view("login");
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle(value = "Register")
    public ModelAndView register(@ModelAttribute("model") UserRegisterBindingModel model) {
        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("model") UserRegisterBindingModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/login");
    }

    @GetMapping("/admin/users-all")
    @PageTitle("All Users")
    public ModelAndView showUsers(ModelAndView modelAndView) {
        List<UserServiceModel> users = userService.allUsers();

        modelAndView.addObject("users", users);

        return super.view(modelAndView, "user/users-all");
    }

    @PostMapping("/root/set-admin/{username}")
    public ModelAndView makeAdmin(@PathVariable String username) {
        userService.setAdminAccess(username);
        return super.redirect("/admin/users-all");
    }

    @PostMapping("/root/set-user/{username}")
    public ModelAndView makeUser(@PathVariable String username) {
        userService.setUserAccess(username);
        return super.redirect("/admin/users-all");
    }

    @PostMapping("/admin/block-user/{username}")
    public ModelAndView blockUser(@PathVariable String username) {
        userService.blockUser(username);
        return super.redirect("/admin/users-all");
    }

    @PostMapping("/admin/unblock-user/{username}")
    public ModelAndView unblockUser(@PathVariable String username) {
        userService.unblockUser(username);
        return super.redirect("/admin/users-all");
    }
}
