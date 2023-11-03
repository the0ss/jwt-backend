package com.theoss.jwtbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoContoller {
    
    @GetMapping
    public ResponseEntity<String> sayhello(){
        return ResponseEntity.ok("Hi from here");
    }
}
