package com.example.spring_crud_boot_restFetch.repository;

import com.example.spring_crud_boot_restFetch.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT DISTINCT r FROM Role r JOIN FETCH r.users")
    List<Role> findAll();
}
