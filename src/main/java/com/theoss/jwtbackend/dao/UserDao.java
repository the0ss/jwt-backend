package com.theoss.jwtbackend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theoss.jwtbackend.entity.User;



public interface UserDao extends JpaRepository<User,String>{

    Optional<User> findById(String email);
    
}
