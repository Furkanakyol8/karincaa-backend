package com.furkan.karincaa.model.dto;

import com.furkan.karincaa.model.entity.User;

public record RegisterDto(UserDto userDto, String token) {
    public RegisterDto(User user, String token){
        this(new UserDto(user), token);
    }
}
