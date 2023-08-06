package com.example.packersandmoversapi.controller;


import com.example.packersandmoversapi.model.AuthRequest;
import com.example.packersandmoversapi.model.AuthResponse;
import com.example.packersandmoversapi.model.UserDto;
import com.example.packersandmoversapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.register(userDto));

    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> login( @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authenticationService.authentication(authRequest));

    }
}
