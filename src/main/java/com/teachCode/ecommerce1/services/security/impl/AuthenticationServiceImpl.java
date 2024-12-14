package com.teachCode.ecommerce1.services.security.impl;

import com.teachCode.ecommerce1.dto.request.SignUpRequest;
import com.teachCode.ecommerce1.dto.request.SigninRequest;
import com.teachCode.ecommerce1.dto.response.JwtAuthenticationResponse;
import com.teachCode.ecommerce1.entities.Role;
import com.teachCode.ecommerce1.entities.User;
import com.teachCode.ecommerce1.repositories.UserRepository;
import com.teachCode.ecommerce1.services.security.AuthenticationService;
import com.teachCode.ecommerce1.services.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public JwtAuthenticationResponse SignUp(SignUpRequest request) {
        // Set default role to USER if no role is provided
        //Role role = request.getRole() != null ? request.getRole() : Role.USER;

        // Create the user with the default role
        /*Role role = request.getRole() != null ? request.getRole() : Role.USER;*/
        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        // Save the user to the database
        userRepository.save(user);
        // Generate JWT token for the new user
        var jwt = jwtService.generateToken(user);

        // Return the JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();


    }

    @Override
    public JwtAuthenticationResponse Signin(SigninRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid "));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).
                userId(user.getId()) // Set the userIdemail
                .role(user.getRole().name()) // Set the role
                .build();

    }}

