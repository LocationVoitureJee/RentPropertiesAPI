package com.rentpropertiesapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record RentAmountRequestDto(
        @NotNull
        @Positive
        double rentAmount) {

}
