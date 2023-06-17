package com.RentProperties.RentProperties.Model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RentalPropertyDto(
        @JsonProperty("id") Long id,
        @JsonProperty("description") String description,
        @JsonProperty("town") String town,
        @JsonProperty("address") String address,
        @JsonProperty("propertyType") PropertyTypeDto propertyType,
        @JsonProperty("rentAmount") Double rentAmount,
        @JsonProperty("securityDepositAmount") Double securityDepositAmount,
        @JsonProperty("area") Double area,
        @JsonProperty("numberOfBedrooms") Integer numberOfBedrooms,
        @JsonProperty("floorNumber") Integer floorNumber,
        @JsonProperty("numberOfFloors") Integer numberOfFloors,
        @JsonProperty("constructionYear") String constructionYear,
        @JsonProperty("energyClassification") EnergyClassificationDto energyClassification,
        @JsonProperty("hasElevator") Boolean hasElevator,
        @JsonProperty("hasIntercom") Boolean hasIntercom,
        @JsonProperty("hasBalcony") Boolean hasBalcony,
        @JsonProperty("hasParkingSpace") Boolean hasParkingSpace)  {
}

