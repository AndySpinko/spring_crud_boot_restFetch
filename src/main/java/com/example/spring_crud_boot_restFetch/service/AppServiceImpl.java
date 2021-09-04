package com.example.spring_crud_boot_restFetch.service;

import com.example.spring_crud_boot_restFetch.model.Role;
import com.example.spring_crud_boot_restFetch.model.User;
import com.example.spring_crud_boot_restFetch.repository.RoleRepository;
import com.example.spring_crud_boot_restFetch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Service
public class AppServiceImpl implements AppService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppServiceImpl(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", email))
                );
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User insertUser(User user, BindingResult bindingResult) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user, BindingResult bindingResult) {
        user.setPassword(user.getPassword().isEmpty() ?
                getUser(user.getId()).getPassword() :
                passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
