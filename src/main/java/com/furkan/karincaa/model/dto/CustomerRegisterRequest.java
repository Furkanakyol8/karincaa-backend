package com.furkan.karincaa.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CustomerRegisterRequest extends RegisterRequest {
    @NotBlank(message = "State cannot be blank!")
    private final String state;
    @NotBlank(message = "City cannot be blank!")
    private final String city;

    public CustomerRegisterRequest(String firstName, String lastName, String email, String password, String phone, String state, String city) {
        super(firstName, lastName, email, password, phone);
        this.state = state;
        this.city = city;
    }
}
