package com.studentbox.api.config.filter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentbox.api.common.CustomAuthentication;
import com.studentbox.api.config.SecurityConfig;
import com.studentbox.api.models.UserDetailsModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static java.util.Objects.isNull;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final SecurityConfig securityConfig;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, SecurityConfig securityConfig) {
        super(authenticationManager);
        this.securityConfig = securityConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(securityConfig.getJwtHeader());
        if(isNull(header) || !header.startsWith(securityConfig.getJwtPrefix())){
            chain.doFilter(request, response);
            return;
        }

        String user = JWT.require(Algorithm.HMAC256(securityConfig.getJwtSecretKey()))
                .build()
                .verify(header.replace(securityConfig.getJwtPrefix(),""))
                .getSubject();

        if(isNull(user)){
            return;
        }

        UserDetailsModel userDetailsModel = new ObjectMapper().readValue(user, UserDetailsModel.class);

        CustomAuthentication token =
                new CustomAuthentication(
                        userDetailsModel.getRole(),
                        "",
                        userDetailsModel.getEmail(),
                        userDetailsModel.getId(),
                        userDetailsModel.getUsername());

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }
}

