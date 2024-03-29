package com.learn.spring.jwt.controller;

import com.learn.spring.jwt.domain.dto.PingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class UserController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/admin")
    public ResponseEntity<Object> adminPing() {
        return ResponseEntity.ok().body(PingResponse.builder()
                .message("Only admin can view this resource!")
                .build());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/user")
    public ResponseEntity<Object> userPing() {
        return ResponseEntity.ok().body(PingResponse.builder()
                .message("Any user can view this resource!")
                .build());
    }
    
}
