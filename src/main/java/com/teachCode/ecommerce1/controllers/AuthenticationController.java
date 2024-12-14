package com.teachCode.ecommerce1.controllers;

import com.teachCode.ecommerce1.dto.request.SignUpRequest;
import com.teachCode.ecommerce1.dto.request.SigninRequest;
import com.teachCode.ecommerce1.dto.response.JwtAuthenticationResponse;
import com.teachCode.ecommerce1.services.security.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")  // Allow only your Angular app's origin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.SignUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request, HttpServletResponse response) {
        JwtAuthenticationResponse jwtResponse = authenticationService.Signin(request);

        if (jwtResponse != null && jwtResponse.getToken() != null) {
            // Set the token in the response header
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, X-Pingother, Origin, X-Requested-with, Content-Type, Accept, X-Custom-header");
            response.setHeader("Authorization", "Bearer " + jwtResponse.getToken());            // Return a response with user details in the body
            JSONObject responseBody = new JSONObject();
            responseBody.put("userID", jwtResponse.getUserId());
            responseBody.put("role", jwtResponse.getRole());
            return ResponseEntity.ok(jwtResponse);
        } else {
            return ResponseEntity.badRequest().body(jwtResponse); // Assuming jwtResponse can be null
        }
    }
}

