package com.example.spring_crud_boot_restFetch.service;

import com.example.spring_crud_boot_restFetch.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
}
