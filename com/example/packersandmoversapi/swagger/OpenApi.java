package com.example.packersandmoversapi.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                  email = "deepak42844@gmail.com",
                  name ="Deepak Das"
                ),
                title = "Simple api for storing user phone no. and message",
                description = "This api is for packer or mover website here user can fill the simple form and bases of that record company can contact them",
                version = "1.0.0"
        ),
        servers = {
         @Server(
                 url = "http://localhost:8080",
                 description = "General Local host address"
         )
        }
//        ,
//
        // for 100% secure endpoint
//        security = {
//                @SecurityRequirement(
//                        name = "JwtAuth"
//                )
//        }
)
@SecurityScheme(
        name = "JwtAuth",
        description = "jwt authentication",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApi {
}
