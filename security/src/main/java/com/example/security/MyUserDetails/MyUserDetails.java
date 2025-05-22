package com.example.security.MyUserDetails;

import com.example.security.entity.User;
import com.example.security.services.RoleServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
public class MyUserDetails implements UserDetails {
    private User user;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean isLoggedIn = false;

    public MyUserDetails(User user, RoleServices roleServices) {
        this.user = user;
        this.authorities = roleServices.findGrandAuthority(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        this.user = user;
        return user;
    }
}
