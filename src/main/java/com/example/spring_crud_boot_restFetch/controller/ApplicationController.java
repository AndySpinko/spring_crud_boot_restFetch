package com.example.spring_crud_boot_restFetch.controller;

import com.example.spring_crud_boot_restFetch.model.User;
import com.example.spring_crud_boot_restFetch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class ApplicationController {

    private final UserService userService;

    @Autowired
    public ApplicationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getPage(Model model, HttpSession session, @Nullable Authentication auth) {
        if (Objects.isNull(auth)) {
            model.addAttribute("authenticatedName", session.getAttribute("authenticatedName"));
            session.removeAttribute("authenticatedName");

            model.addAttribute("authenticationException", session.getAttribute("authenticationException"));
            session.removeAttribute("authenticationException");

            return "login-page";
        }

        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        if (user.hasRole("ROLE_ADMIN")) {
            return "main-page";
        }

        if (user.hasRole("ROLE_USER")) {
            return "user-page";
        }
        return "login-page";
    }
}
