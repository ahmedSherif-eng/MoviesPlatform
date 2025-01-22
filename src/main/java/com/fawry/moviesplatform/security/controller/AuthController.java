package com.fawry.moviesplatform.security.controller;

import com.fawry.moviesplatform.security.CustomUserDetailsService;
import com.fawry.moviesplatform.security.DTO.AuthenticationRequest;
import com.fawry.moviesplatform.security.DTO.AuthenticationResponse;
import com.fawry.moviesplatform.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            System.out.println("Login attempt for user: " + authenticationRequest.getUsername());
            
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
            
            System.out.println("Authentication successful");
            
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            System.out.println("User authorities: " + userDetails.getAuthorities());
            
            final String jwt = jwtUtil.generateToken(userDetails);
            System.out.println("JWT token generated successfully");
            
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
