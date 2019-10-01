package com.udemy.appws.service.impl;

import com.udemy.appws.bean.UserResponse;
import com.udemy.appws.entity.User;
import com.udemy.appws.repository.UserRepository;
import com.udemy.appws.service.UserService;
import com.udemy.appws.util.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.udemy.appws.util.RandomString.randomString;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static Converter<User, UserResponse> converter = (user -> {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    });

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserServiceImpl() {

    }

    @Override
    public UserResponse findById(Long id) {
        User user = this.userRepository.findById(id).get();
        return converter.convert(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> responses = new ArrayList<>();
        Iterable<User> users = this.userRepository.findAll();

        users.forEach(user -> {
            responses.add(converter.convert(user));
        });
        return responses;
    }

    @Override
    public UserResponse saveUser(User user) {
        user.setUserID(randomString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return converter.convert(user);
    }

    @Override
    public String deleteById(Long id) {
        this.userRepository.deleteById(id);
        return "User was deleted";
    }

    @Override
    public UserResponse findByUserID(String userID) {
        User user = this.userRepository.findAllByUserID(userID);
        if (user == null) throw new UsernameNotFoundException(userID);

        return converter.convert(user);
    }

    @Override
    public UserResponse findByUsername(String username) {
        User user = this.userRepository.findAllByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return converter.convert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User response = this.userRepository.findAllByUsername(username);

        if (response == null) throw new UsernameNotFoundException(username);

        return new
                org.springframework.security.core.userdetails.User
                (response.getUsername(),
                        response.getPassword(), new ArrayList<>()
                );
    }
}