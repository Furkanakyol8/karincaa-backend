package com.furkan.karincaa.service.impl;

import com.furkan.karincaa.exception.AlreadyExistException;
import com.furkan.karincaa.model.dto.CustomerDto;
import com.furkan.karincaa.model.dto.CustomerRegisterDto;
import com.furkan.karincaa.model.dto.CustomerRegisterRequest;
import com.furkan.karincaa.model.entity.Customer;
import com.furkan.karincaa.repository.CustomerRepository;
import com.furkan.karincaa.security.helper.JwtHelper;
import com.furkan.karincaa.security.helper.UserHelper;
import com.furkan.karincaa.service.CustomerService;
import com.furkan.karincaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    @Override
    public CustomerRegisterDto register(CustomerRegisterRequest customerRegisterRequest) {
        if(userService.existsByPhone(customerRegisterRequest.getPhone())) {
            throw new AlreadyExistException("Phone already exists!");
        }

        String encodedPassword = passwordEncoder.encode(customerRegisterRequest.getPassword());

        Customer customer = Customer.builder()
                .firstName(customerRegisterRequest.getFirstName())
                .lastName(customerRegisterRequest.getLastName())
                .phone(customerRegisterRequest.getPhone())
                .email(customerRegisterRequest.getEmail())
                .password(encodedPassword)
                .state(customerRegisterRequest.getState())
                .city(customerRegisterRequest.getCity())
                .build();

        customerRepository.save(customer);

        String token = jwtHelper.generateToken(customer);

        return new CustomerRegisterDto(customer, token);
    }

    @Override
    public CustomerDto me(){
        return new CustomerDto(UserHelper.getLoggedCustomer());
    }
}
