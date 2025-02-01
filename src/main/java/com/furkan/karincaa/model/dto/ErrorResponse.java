package com.furkan.karincaa.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    public static final ErrorResponseBuilder GENERAL = ErrorResponse.builder()
            .timestamp(Timestamp.valueOf(LocalDateTime.now()))
            .message("An error occured!");

    private Timestamp timestamp;

    private String message;

    public ErrorResponse(String message) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.message = message;
    }
}