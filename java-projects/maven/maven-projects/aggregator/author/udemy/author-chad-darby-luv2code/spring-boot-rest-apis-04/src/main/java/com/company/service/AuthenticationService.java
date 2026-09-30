package com.company.service;

import com.company.request.AuthenticationRequest;
import com.company.request.RegisterRequest;
import com.company.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
