package com.example.security.services;

import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.models.Application;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
@Service
public class AppService {
    private List<Application> applications;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();

    }

    public List<Application> allApplications() {
        return applications;

    }

    public Application appById(int id) {
        return applications.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);

    }

    public void addUser(User user){
         user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

    }


    public Page<User> findAll(Integer page){
      return userRepository.findAll(PageRequest.of(page-1,5, Sort.by("id").ascending()));


    }

    public void addRole(Long user_id, String newRole) {
        Optional<Role> role = roleRepository.findByName(newRole);
        if(role.isPresent()){
            Long role_id = role.get().getId();
            userRepository.addRoleToUser(user_id,role_id);
        }else {
            Role newrole = new Role();
            newrole.setName(newRole);
            roleRepository.save(newrole);
            userRepository.addRoleToUser(user_id,newrole.getId());

        }



    }
}
