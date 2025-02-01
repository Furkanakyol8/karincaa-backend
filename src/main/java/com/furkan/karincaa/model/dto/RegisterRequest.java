package com.furkan.karincaa.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public abstract class RegisterRequest {
    @NotBlank(message = "First Name cannot be blank!")
    private final String firstName;
    @NotBlank(message = "Last Name cannot be blank!")
    private final String lastName;
    @NotBlank(message = "Email cannot be blank!")
    private final String email;
    @NotBlank(message = "Password cannot be blank!")
    private final String password;
    @NotBlank(message = "Phone cannot be blank!")
    private final String phone;
}
