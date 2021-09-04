package com.example.spring_crud_boot_restFetch.controller;

import com.example.spring_crud_boot_restFetch.model.Role;
import com.example.spring_crud_boot_restFetch.model.User;
import com.example.spring_crud_boot_restFetch.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationRestController {
    private final AppService appService;

    @Autowired
    public ApplicationRestController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = appService.findAllUsers();
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appService.findAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = appService.getUser(id);

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> insert(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(appService.insertUser(user, bindingResult));
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(appService.updateUser(user, bindingResult));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Iterable<Role>> findAllRoles() {
        List<Role> roles = (List<Role>) appService.findAllRoles();
        if(roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appService.findAllRoles());
    }
}
