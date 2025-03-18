package com.example.capstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "ID must not be empty")
    private String id;

    @NotEmpty(message = "username cant be empty")
    @Size(min = 5, message = "username must be more than 5 characters long")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain letters and digits")
    private String password;

    @NotEmpty(message = "email cant be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "role cant be empty")
    @Pattern(regexp = "Admin|Customer", message = "Role must be either Admin or Customer")
    private String role;

    @NotNull(message = "cant be empty")
    @PositiveOrZero(message = "Balance must be positive or zero")
    private Double balance;



}