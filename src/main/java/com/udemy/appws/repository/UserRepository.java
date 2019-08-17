package com.udemy.appws.repository;

import com.udemy.appws.bean.UserResponse;
import com.udemy.appws.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    void deleteById(Long id);
    User findAllByUsername(String username);
    User findAllByUserID(String userID);
}
