package com.revature.RevShopServer.services.interfaces;

import com.revature.RevShopServer.dtos.JwtAuthenticationResponse;
import com.revature.RevShopServer.dtos.SignInRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signin(SignInRequest request);
}
