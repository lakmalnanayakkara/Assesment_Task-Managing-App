package com.taskManagerApp.backend.configuration;

import com.taskManagerApp.backend.exception.BadCredentialsException;
import com.taskManagerApp.backend.exception.ValidationFailedException;
import com.taskManagerApp.backend.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        String requestURI = request.getRequestURI();
        if (requestURI.contains("/api/v1/user/sign-up") || requestURI.contains("/api/v1/user/sign-in")) {
            filterChain.doFilter(request, response);
        }

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.getUserNameFromToken(jwtToken);
            }catch (Exception e) {
                throw new BadCredentialsException("Error extracting username from token");
            }
        }else {
            throw new ValidationFailedException("Invalid or missing Authorization header");
        }
    }
}
