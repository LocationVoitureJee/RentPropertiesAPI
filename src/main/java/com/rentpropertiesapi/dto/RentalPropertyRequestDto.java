package com.rentpropertiesapi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record RentalPropertyRequestDto(
        @NotBlank
        @Size(max = 200)
        String description,

        @NotBlank
        @Size(max = 100)
        String town,

        @NotBlank
        @Size(max = 200)
        String address,

        @NotNull
        String propertyType,

        @NotNull
        @Positive
        double rentAmount,

        @NotNull
        @Positive
        double securityDepositAmount,

        @NotNull
        @Positive
        double area,

        @Min(0)
        int bedroomsCount,

        @Min(0)
        int floorNumber,

        @Min(0)
        int numberOfFloors,

        @Digits(integer = 4, fraction = 0)
        int constructionYear,

        String energyClassification,

        boolean hasElevator,

        boolean hasIntercom,

        boolean hasBalcony,

        boolean hasParkingSpace) {

}
