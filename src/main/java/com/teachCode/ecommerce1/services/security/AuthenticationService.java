package com.teachCode.ecommerce1.services.security;

import com.teachCode.ecommerce1.dto.request.SignUpRequest;
import com.teachCode.ecommerce1.dto.request.SigninRequest;
import com.teachCode.ecommerce1.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
JwtAuthenticationResponse SignUp(SignUpRequest request);
JwtAuthenticationResponse Signin(SigninRequest request);



}
