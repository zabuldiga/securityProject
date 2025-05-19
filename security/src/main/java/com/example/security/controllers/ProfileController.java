package com.example.security.controllers;

import com.example.security.dto.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public ProfileDto getCurrentUsers(Principal principal){
        System.out.println("===========================================");
        return new ProfileDto(principal.getName());

    }
}
