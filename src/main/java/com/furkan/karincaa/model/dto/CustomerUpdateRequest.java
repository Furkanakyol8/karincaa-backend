package com.furkan.karincaa.model.dto;

public record CustomerUpdateRequest (String firstName, String lastName, String email, String phone, String state, String city) {
}
