package com.furkan.karincaa.model.dto;

import com.furkan.karincaa.model.entity.Customer;

public record CustomerRegisterDto(CustomerDto customerDto, String token) {
    public CustomerRegisterDto(Customer customer, String token){
        this(new CustomerDto(customer), token);
    }
}
