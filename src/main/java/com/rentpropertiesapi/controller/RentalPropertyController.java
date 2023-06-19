package com.rentpropertiesapi.controller;

import com.rentpropertiesapi.dto.RentAmountRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyResponseDto;
import com.rentpropertiesapi.service.RentalPropertyService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/rent-properties-api")
@Validated
public class RentalPropertyController {

    private final RentalPropertyService rentalPropertyService;

    public RentalPropertyController(RentalPropertyService rentalPropertyService) {
        this.rentalPropertyService = rentalPropertyService;
    }

    @GetMapping("/rental-properties")
    public List<RentalPropertyResponseDto> getRentalProperties() {
        return rentalPropertyService.getRentalProperties();
    }

    @GetMapping("/rental-properties/{id}")
    public RentalPropertyResponseDto getRentalPropertyById(@PathVariable String id) {
        return rentalPropertyService.getRentalPropertyById(id);
    }

    @PostMapping("/rental-properties")
    @ResponseStatus(CREATED)
    public void createRentalProperty(@Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        rentalPropertyService.createRentalProperty(rentalPropertyRequestDto);
    }

    @PutMapping("/rental-properties/{id}")
    @ResponseStatus(OK)
    public void updateRentalProperty(@PathVariable UUID id, @Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);
    }

    @PatchMapping("/rental-properties/{id}")
    @ResponseStatus(OK)
    public void updateRentalPropertyAmount(@PathVariable UUID id, @Valid @RequestBody RentAmountRequestDto rentAmountRequestDto) {
        rentalPropertyService.updateRentalPropertyAmount(id, rentAmountRequestDto);
    }

    @DeleteMapping("/rental-properties/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteRentalPropertyById(@PathVariable UUID id) {
        rentalPropertyService.deleteRentalPropertyById(id);
    }
}
