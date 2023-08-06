package com.example.packersandmoversapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    @Email
    String email;
    @NotEmpty @Size(min = 4)
    String password;
}
