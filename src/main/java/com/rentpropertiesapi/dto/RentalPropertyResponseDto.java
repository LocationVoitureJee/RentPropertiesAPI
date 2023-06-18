package com.rentpropertiesapi.dto;

import lombok.Builder;

@Builder
public record RentalPropertyResponseDto(String description,
                                        String address,
                                        String town,
                                        String propertyType,
                                        double rentAmount,
                                        double securityDepositAmount,
                                        double area) {
}
