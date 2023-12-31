package com.theoss.jwtbackend.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theoss.jwtbackend.configuration.JwtTokenProvider;
import com.theoss.jwtbackend.dao.UserDao;
import com.theoss.jwtbackend.entity.AuthenticationRequest;
import com.theoss.jwtbackend.entity.JwtResponse;
import com.theoss.jwtbackend.entity.RegisterRequest;
import com.theoss.jwtbackend.entity.Role;
import com.theoss.jwtbackend.entity.User;

import lombok.AllArgsConstructor;
import lombok.var;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    
    public JwtResponse register(RegisterRequest request){
        var user=User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .roles(request.getRoles())
                    .build();
                    
        userDao.save(user);
        
        var jwtToken=jwtTokenProvider.generateToken(user);
        return JwtResponse.builder()
            .token(jwtToken)
            .role(user.getRoles())
            .build();
    }

    public JwtResponse authenticate(AuthenticationRequest request){
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
      var user=userDao.findByEmail(request.getEmail()).orElseThrow();
      var jwtToken=jwtTokenProvider.generateToken(user);
        return JwtResponse.builder()
            .token(jwtToken)
            .role(user.getRoles())
            .build();
    }

}
