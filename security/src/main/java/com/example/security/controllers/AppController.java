package com.example.security.controllers;

import com.example.security.dto.UserDto;
import com.example.security.entity.User;
import com.example.security.models.Application;
import com.example.security.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/apps/")
@AllArgsConstructor
public class AppController {
    private AppService appService;

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome to the  unprotected page";

    }
    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> showAll(@RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        if (page < 1) {
            page = 1;

        }
        return appService.findAll(page);
    }

    @GetMapping("products")
    public String product() {
        return "product";

    }



    @GetMapping("all-app")
//    and hasAuthority('ALL-APP')
    @PreAuthorize("hasRole('ROLE_USER') and hasAuthority('ALL-APP') ")
    public List<Application> allApps() {
        return appService.allApplications();

    }


    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and hasAuthority('ID')")
    public Application appById(@PathVariable int id) {
        return appService.appById(id);

    }
    @GetMapping("user-info")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        return ResponseEntity.ok(Map.of("username", principal.getName()));
    }
//    @PostMapping("new-user/")
//    public void addUser(@RequestBody User user){
//        user.setEmail("test@example.com");
//           appService.addUser(user);
//
//    }

    @PostMapping("new-user/")
    public void addUser(@RequestBody UserDto userDto){
       User user = new User();
       user.setEmail("test@example.com");
       user.setUsername(userDto.getUsername());
       user.setPassword(userDto.getPassword());
        appService.addUser(user);

    }

    @PostMapping("/{id}/add-role")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public void addRole(@PathVariable Long id,@RequestBody String newRole){
        System.out.println("=========newRole: " + newRole);
        appService.addRole(id,newRole);

    }

}
