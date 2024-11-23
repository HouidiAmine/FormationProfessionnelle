package com.teachCode.ecommerce1.dto.request;

public record NotificationDto(
        String userId,
        String message,
        boolean status
) {
}
