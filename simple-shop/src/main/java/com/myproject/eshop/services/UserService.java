package com.myproject.eshop.services;

import com.myproject.eshop.data.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    List<UserServiceModel> allUsers();

    void setAdminAccess(String username);

    void setUserAccess(String username);
}
