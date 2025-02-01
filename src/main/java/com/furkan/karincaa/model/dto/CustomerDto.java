package com.furkan.karincaa.model.dto;

import com.furkan.karincaa.model.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerDto extends UserDto {
    private final String state;
    private final String city;
    public CustomerDto(Customer customer) {
        super(customer);
        this.state = customer.getState();
        this.city = customer.getCity();
    }
}
