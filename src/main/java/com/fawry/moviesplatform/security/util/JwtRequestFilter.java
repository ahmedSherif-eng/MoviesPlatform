package com.fawry.moviesplatform.security.util;

import com.fawry.moviesplatform.security.service.CustomUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ApplicationContext applicationContext;
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    private CustomUserDetailsServiceImpl userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, ApplicationContext applicationContext) {
        this.jwtUtil = jwtUtil;
        this.applicationContext = applicationContext;
    }

    private CustomUserDetailsServiceImpl getUserDetailsService() {
        if (userDetailsService == null) {
            userDetailsService = applicationContext.getBean(CustomUserDetailsServiceImpl.class);
        }
        return userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (request.getRequestURI().equals("/api/auth/login") || request.getRequestURI().equals("/api/auth/register")) {
                chain.doFilter(request, response);
                return;
            }

            final String authorizationHeader = request.getHeader("Authorization");
            logger.info("Request URI: {}", request.getRequestURI());
            logger.info("Authorization Header: {}", authorizationHeader);

            String username = null;
            String jwt = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                try {
                    username = jwtUtil.extractUsername(jwt);
                    logger.info("Extracted username from token: {}", username);
                } catch (SignatureException e) {
                    logger.error("Invalid JWT signature: {}", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid token signature");
                    return;
                } catch (ExpiredJwtException e) {
                    logger.error("JWT token expired: " + e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token has expired");
                    return;
                } catch (JwtException e) {
                    logger.error("JWT token error: " + e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid token");
                    return;
                }
            } else {
                logger.error("No valid authorization header found");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = getUserDetailsService().loadUserByUsername(username);
                logger.info("User authorities: {}", userDetails.getAuthorities());
                
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                userDetails, 
                                null, 
                                userDetails.getAuthorities()
                            );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Token validated successfully");
                } else {
                    logger.error("Token validation failed");
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Error processing JWT token: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Error processing token: " + e.getMessage());
        }
    }

}