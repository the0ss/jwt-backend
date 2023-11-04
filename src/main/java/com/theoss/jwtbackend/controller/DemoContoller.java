package com.theoss.jwtbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoContoller {
    
    @GetMapping("/forAdmin")
    public ResponseEntity<String> adminhello(){
        return ResponseEntity.ok("Hi from Admin");
    }
    @GetMapping("/forUser")
    public ResponseEntity<String> userhello(){
        return ResponseEntity.ok("Hi from User");
    }
}
