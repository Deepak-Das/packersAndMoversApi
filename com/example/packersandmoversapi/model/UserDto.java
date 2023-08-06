package com.example.packersandmoversapi.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.example.packersandmoversapi.util.Role;

@Data
public class UserDto {
    @NotEmpty(message = "Must have first name")
    private String firstName;
    @NotEmpty(message = "Must have last name")
    private String lastName;
    @NotEmpty(message = "Must have email address")
    @Email(message = "please provide valid email")
    private String email;

    @NotEmpty(message = "password cant be empty")
    @Min(value = 4,message = "password must contain at least 4 character")
    private String password;


    @NotNull(message = "role can't be empty")
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
