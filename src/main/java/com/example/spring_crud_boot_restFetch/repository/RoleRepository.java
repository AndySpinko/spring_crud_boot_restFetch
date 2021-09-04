package com.example.spring_crud_boot_restFetch.repository;

import com.example.spring_crud_boot_restFetch.model.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Integer> {
}
