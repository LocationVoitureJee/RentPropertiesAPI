package com.RentProperties.RentProperties.Model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PropertyTypeDto(@JsonProperty("id") Long id,
                              @JsonProperty("type") String type) {
}
