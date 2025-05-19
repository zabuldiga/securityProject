package com.example.security.services;

import com.example.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Slf4j
@Service
public class RoleServices {



public Collection<? extends GrantedAuthority> findGrandAuthority(User user){
    return user.getRoles().stream()
            .flatMap(role -> {
                Stream<SimpleGrantedAuthority> roleAuth = Stream.of(new SimpleGrantedAuthority(role.getName()));
                Stream<SimpleGrantedAuthority> privilegeAuth = role.getPrivilegies().stream()
                        .map(priv -> new SimpleGrantedAuthority(priv.getPrivilegies_name()));
                return Stream.concat(roleAuth, privilegeAuth);
            }).collect(Collectors.toList());
}



}
