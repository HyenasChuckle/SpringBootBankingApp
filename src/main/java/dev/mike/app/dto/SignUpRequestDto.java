package dev.mike.app.dto;

import jakarta.validation.constraints.NotNull;

public record SignUpRequestDto(
        @NotNull(message = "Username is required!")
        String username,

        @NotNull(message = "Password is required!")
        String password) {}
