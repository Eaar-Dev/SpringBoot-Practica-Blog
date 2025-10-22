package com.example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.model.User;

@Repository
public interface UserInterface extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
