package com.furkan.karincaa.service;

import com.furkan.karincaa.model.dto.CustomerDto;
import com.furkan.karincaa.model.dto.CustomerRegisterDto;
import com.furkan.karincaa.model.dto.CustomerRegisterRequest;
import com.furkan.karincaa.model.dto.CustomerUpdateRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface CustomerService {
    CustomerRegisterDto register(CustomerRegisterRequest customerRegisterRequest);

    CustomerDto me();

    CustomerDto update(@PathVariable UUID id, CustomerUpdateRequest customerUpdateRequest);

    void delete(@PathVariable UUID id);
}
