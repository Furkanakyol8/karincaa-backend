package com.furkan.karincaa.controller;

import com.furkan.karincaa.model.dto.*;
import com.furkan.karincaa.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController{

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterDto> register(@RequestBody @Valid CustomerRegisterRequest customerRegisterRequest) {
        return ResponseEntity.ok(customerService.register(customerRegisterRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> me() {
        return ResponseEntity.ok(customerService.me());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable UUID id, @RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
        return ResponseEntity.ok(customerService.update(id, customerUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable @Valid UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
