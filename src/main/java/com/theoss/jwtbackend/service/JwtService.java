package com.theoss.jwtbackend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.theoss.jwtbackend.dao.UserDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtService implements UserDetailsService{

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findById(email).orElseThrow(()->new UsernameNotFoundException(email+"not found"));
    }
    
}
