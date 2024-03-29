package com.learn.spring.jwt.controller;

import com.learn.spring.jwt.component.TokenProvider;
import com.learn.spring.jwt.domain.dao.User;
import com.learn.spring.jwt.domain.dto.LoginRequest;
import com.learn.spring.jwt.domain.dto.LoginResponse;
import com.learn.spring.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailsService")
    private UserService userService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {

        User user = (User) userService.loadUserByUsername(request.getUsername());
        log.info("User :: {}", user);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }


        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        return ResponseEntity.ok(new LoginResponse(token));
    }
    
}
