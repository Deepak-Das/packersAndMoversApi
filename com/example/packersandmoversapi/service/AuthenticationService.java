package com.example.packersandmoversapi.service;


import com.example.packersandmoversapi.model.AuthRequest;
import com.example.packersandmoversapi.model.AuthResponse;
import com.example.packersandmoversapi.model.UserDto;

public interface AuthenticationService {
    public AuthResponse register(UserDto userDto) ;
    public AuthResponse authentication(AuthRequest authRequest);
}
