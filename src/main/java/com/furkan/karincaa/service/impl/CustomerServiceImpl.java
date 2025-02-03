package com.furkan.karincaa.service.impl;

import com.furkan.karincaa.exception.AlreadyExistException;
import com.furkan.karincaa.exception.NotFoundException;
import com.furkan.karincaa.model.dto.CustomerDto;
import com.furkan.karincaa.model.dto.CustomerRegisterDto;
import com.furkan.karincaa.model.dto.CustomerRegisterRequest;
import com.furkan.karincaa.model.dto.CustomerUpdateRequest;
import com.furkan.karincaa.model.entity.Customer;
import com.furkan.karincaa.repository.CustomerRepository;
import com.furkan.karincaa.security.helper.JwtHelper;
import com.furkan.karincaa.security.helper.UserHelper;
import com.furkan.karincaa.service.CustomerService;
import com.furkan.karincaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

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


    @Override
    public CustomerDto update(@PathVariable UUID id, CustomerUpdateRequest customerUpdateRequest) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        customer.setFirstName(customerUpdateRequest.firstName());
        customer.setLastName(customerUpdateRequest.lastName());
        customer.setPhone(customerUpdateRequest.phone());
        customer.setEmail(customerUpdateRequest.email());
        customer.setState(customerUpdateRequest.state());
        customer.setCity(customerUpdateRequest.city());

        customerRepository.save(customer);

        return new CustomerDto(customer);
    }

    @Override
    public void delete(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }


}
