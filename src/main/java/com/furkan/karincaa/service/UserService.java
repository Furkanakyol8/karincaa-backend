package com.furkan.karincaa.service;

import com.furkan.karincaa.model.entity.User;

public interface UserService {

    User findByPhone(String phone);


    boolean existsByPhone(String phone);
}
