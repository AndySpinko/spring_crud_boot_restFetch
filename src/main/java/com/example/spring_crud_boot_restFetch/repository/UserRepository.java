package com.example.spring_crud_boot_restFetch.repository;

import com.example.spring_crud_boot_restFetch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
    //User findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles")
    List<User> findAll();


    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id")
    Optional<User> findById(@Param("id") Long id);
}
