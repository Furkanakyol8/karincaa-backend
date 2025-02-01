package com.furkan.karincaa.service;

import com.furkan.karincaa.model.dto.CustomerDto;
import com.furkan.karincaa.model.dto.CustomerRegisterDto;
import com.furkan.karincaa.model.dto.CustomerRegisterRequest;

public interface CustomerService {
    CustomerRegisterDto register(CustomerRegisterRequest customerRegisterRequest);

    CustomerDto me();
}
