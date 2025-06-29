package com.project.secure.notes.models.exception;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;

    public ErrorDetails(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
