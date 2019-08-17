package com.udemy.appws.service;

import com.udemy.appws.bean.UserResponse;
import com.udemy.appws.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {
    public UserResponse findById(Long id);
    public List<UserResponse> findAll();
    public UserResponse saveUser(User user);
    public String deleteById(Long id);
    public UserResponse findByUserID(String userID);
    public UserResponse findByUsername(String username);
}
