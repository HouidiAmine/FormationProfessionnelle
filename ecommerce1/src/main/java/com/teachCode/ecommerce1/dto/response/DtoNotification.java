package com.teachCode.ecommerce1.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DtoNotification(
        String userId,
        String message,
        boolean status,
        LocalDate datePosted
) {

}
