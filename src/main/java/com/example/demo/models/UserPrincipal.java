package com.example.demo.models;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;
    String username = null;
    String password = null;
    Set<SimpleGrantedAuthority> authorities;

    public UserPrincipal() {
        authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
    }
    public UserPrincipal(Users user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
