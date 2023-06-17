package com.RentProperties.RentProperties.Model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;


@Builder
public record EnergyClassificationDto(@JsonProperty("id") Long id,
                                      @JsonProperty("classification") String classification) {
}
