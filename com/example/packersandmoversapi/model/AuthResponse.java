package com.example.packersandmoversapi.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    String token;
}
