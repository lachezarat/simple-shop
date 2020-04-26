package com.myproject.eshop.services;

import com.myproject.eshop.data.entities.User;
import com.myproject.eshop.data.models.service.UserServiceModel;
import com.myproject.eshop.error.UserWasBlockedException;
import com.myproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        Optional<User> checkUsername = userRepository.findByUsername(userServiceModel.getUsername());

        Optional<User> checkEmail = userRepository.findByEmail(userServiceModel.getEmail());


        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        userServiceModel.setJoinDate(new Date());
        userServiceModel.setEnabled(true);
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        if (!user.isEnabled()) {
            throw new UserWasBlockedException("User was blocked!");
        }

        return user;
    }

    @Override
    public List<UserServiceModel> allUsers() {
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getJoinDate))
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void setAdminAccess(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        UserServiceModel userServiceModel = modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));

        userRepository.saveAndFlush(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public void setUserAccess(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        UserServiceModel userServiceModel = modelMapper.map(user, UserServiceModel.class);

        userServiceModel.getAuthorities().clear();
        userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));

        userRepository.saveAndFlush(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public boolean isUsernameAlreadyInUse(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent();
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.isPresent();
    }

    @Override
    public void blockUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        user.setEnabled(false);

        userRepository.saveAndFlush(user);
    }

    @Override
    public void unblockUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        user.setEnabled(true);

        userRepository.saveAndFlush(user);
    }
}
