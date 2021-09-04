package com.example.spring_crud_boot_restFetch.service;

import com.example.spring_crud_boot_restFetch.model.Role;
import com.example.spring_crud_boot_restFetch.model.User;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import javax.servlet.http.HttpSession;
import java.util.List;

public interface AppService extends UserDetailsService {

    List<User> findAllUsers();

    User getUser(Long id);

    User insertUser(User user, BindingResult bindingResult);

    User updateUser(User user, BindingResult bindingResult);

    void deleteUser(Long id);

    Iterable<Role> findAllRoles();

    String getPage(Model model, HttpSession session, @Nullable Authentication auth);
}
