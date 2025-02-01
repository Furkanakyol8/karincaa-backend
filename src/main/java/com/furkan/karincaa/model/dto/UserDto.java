package com.furkan.karincaa.model.dto;


import com.furkan.karincaa.model.entity.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

}
