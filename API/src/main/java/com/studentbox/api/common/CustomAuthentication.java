package com.studentbox.api.common;

import com.studentbox.api.entities.Role;
import com.studentbox.api.exception.NotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;

public class CustomAuthentication implements Authentication {
    private Role userRole;
    private Object credentials;
    private Object details;
    private Object principal;
    private String name;

    public CustomAuthentication(Role role, Object credentials, Object details, Object principal, String name){
        this.userRole = role;
        this.credentials = credentials;
        this.details = details;
        this.principal = principal;
        this.name = name;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(userRole);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}

    @Override
    public String getName() {
        return name;
    }

    public static Long getAuthenticationPrincipal() {
        Authentication authentication = getAuthentication();

        if(authentication.getPrincipal() instanceof Long id){
            return id;
        }
        throw new NotAuthenticatedException();
    }

    public static CustomAuthentication getAuthentication() {
        return (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}