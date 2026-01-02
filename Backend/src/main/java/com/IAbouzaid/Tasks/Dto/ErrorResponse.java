package com.IAbouzaid.Tasks.Dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
