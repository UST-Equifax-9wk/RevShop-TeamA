package com.revature.revshopserver.services.interfaces;

import com.revature.revshopserver.dtos.JwtAuthenticationResponse;
import com.revature.revshopserver.dtos.SignInRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signin(SignInRequest request);
}
