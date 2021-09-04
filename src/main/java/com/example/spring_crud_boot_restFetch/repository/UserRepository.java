package com.example.spring_crud_boot_restFetch.repository;

import com.example.spring_crud_boot_restFetch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByEmail(String email);
}
