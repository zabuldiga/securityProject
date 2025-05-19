package com.example.security.services;

import com.example.security.entity.User;
import com.example.security.MyUserDetails.MyUserDetails;
import com.example.security.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RoleServices services;





    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(u -> new MyUserDetails(u,services))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

    }
     public int findIdByUser(String username){


        Optional<User> user = userRepository.findByUsername(username);
        int a = Math.toIntExact(user.get().getId());
        return a;

     }

}
