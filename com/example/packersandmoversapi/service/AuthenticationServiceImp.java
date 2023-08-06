package com.example.packersandmoversapi.service;


import com.example.packersandmoversapi.exception.UserAlreadyExistException;
import com.example.packersandmoversapi.exception.UserNotExistException;
import lombok.RequiredArgsConstructor;
import com.example.packersandmoversapi.model.AuthRequest;
import com.example.packersandmoversapi.model.AuthResponse;
import com.example.packersandmoversapi.model.User;
import com.example.packersandmoversapi.model.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.packersandmoversapi.repository.UserRepository;
import com.example.packersandmoversapi.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(UserDto userDto) {

        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        User user;

        if (!exists) {
            user = User
                    .builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .role(userDto.getRole())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .build();
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException();
        }

        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();


    }


    public AuthResponse authentication(AuthRequest authRequest) {
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(UserNotExistException::new);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                        authRequest.getPassword())
        );
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
