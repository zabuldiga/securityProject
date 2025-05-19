package com.example.security.controllers;

import com.example.security.dto.JwtRequest;
import com.example.security.dto.JwtResponse;
import com.example.security.services.UserDetailsServices;
import com.example.security.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import products.example.Exception.AppError;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServices userDetailsServices;
    private final AuthenticationManager authenticationManager;



    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        if (authRequest.getUsername() == null || authRequest.getPassword() == null) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Username and password must not be null"), HttpStatus.BAD_REQUEST);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"),HttpStatus.UNAUTHORIZED);

        }
        UserDetails userDetails = userDetailsServices.loadUserByUsername(authRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);
        return   ResponseEntity.ok(new JwtResponse(token));
    }


}
