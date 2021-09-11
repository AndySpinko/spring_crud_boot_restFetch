package com.example.spring_crud_boot_restFetch.service;

import com.example.spring_crud_boot_restFetch.model.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User getUser(Long id);

    User insertUser(User user, BindingResult bindingResult);

    User updateUser(User user, BindingResult bindingResult);

    void deleteUser(Long id);

}
