package com.rentpropertiesapi.controller;

import com.rentpropertiesapi.models.EnergyClassificationEntity;
import com.rentpropertiesapi.models.PropertyTypeEntity;
import com.rentpropertiesapi.models.RentalPropertyEntity;
import com.rentpropertiesapi.dto.RentAmountRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyResponseDto;
import com.rentpropertiesapi.exception.NotFoundEnergyClassificationException;
import com.rentpropertiesapi.exception.NotFoundPropertyTypeException;
import com.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import com.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import com.rentpropertiesapi.repository.EnergyClassificationRepository;
import com.rentpropertiesapi.repository.PropertyTypeRepository;
import com.rentpropertiesapi.repository.RentalPropertyRepository;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/rent-properties-api")
@Validated
public class RentalPropertyController {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final EnergyClassificationRepository energyClassificationRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    public RentalPropertyController(RentalPropertyRepository rentalPropertyRepository, EnergyClassificationRepository energyClassificationRepository, PropertyTypeRepository propertyTypeRepository, RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.energyClassificationRepository = energyClassificationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    @GetMapping("/rental-properties")
    public List<RentalPropertyResponseDto> getRentalProperties() {
        System.out.println("okkkkkkkk");
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();
        System.out.println(rentalProperties.get(0).getId());
        return rentalPropertyDtoMapper.mapToDtoList(rentalProperties);
    }

    @GetMapping("/rental-properties/{id}")
    public RentalPropertyResponseDto getRentalPropertyById(@PathVariable String id) {
        return rentalPropertyRepository.findById(UUID.fromString(id))
                .map(rentalPropertyDtoMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier " + id + " est introuvable"));
    }

    @PostMapping("/rental-properties")
    @ResponseStatus(CREATED)
    public void createRentalProperty(@Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto){

        PropertyTypeEntity propertyType = propertyTypeRepository.findByDesignation(rentalPropertyRequestDto.propertyType()) .orElseThrow(() -> new NotFoundPropertyTypeException("Le property type " + rentalPropertyRequestDto.propertyType() + " est introuvable"));

        EnergyClassificationEntity energyClassification = energyClassificationRepository.findByDesignation(rentalPropertyRequestDto.energyClassification()).orElseThrow(() -> new NotFoundEnergyClassificationException("Le energy type " + rentalPropertyRequestDto.energyClassification() + " est introuvable"));

        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto, propertyType,energyClassification );

        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    @PutMapping("/rental-properties/{id}")
    @ResponseStatus(OK)
    public void updateRentalProperty(@PathVariable UUID id, @Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {

        RentalPropertyEntity rentalPropertyEntityOld = rentalPropertyRepository.findById(id) .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien  " + id + " est introuvable"));

        PropertyTypeEntity propertyType = propertyTypeRepository.findByDesignation(rentalPropertyRequestDto.propertyType()) .orElseThrow(() -> new NotFoundPropertyTypeException("Le property type " + rentalPropertyRequestDto.propertyType() + " est introuvable"));

        EnergyClassificationEntity energyClassification = energyClassificationRepository.findByDesignation(rentalPropertyRequestDto.energyClassification()).orElseThrow(() -> new NotFoundEnergyClassificationException("Le energy type " + rentalPropertyRequestDto.energyClassification() + " est introuvable"));

        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto, propertyType,energyClassification );
        rentalPropertyEntity.setId(rentalPropertyEntityOld.getId());
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    @PatchMapping("/rental-properties/{id}")
    @ResponseStatus(OK)
    public void updateRentalPropertyAmount(@PathVariable UUID id, @Valid @RequestBody RentAmountRequestDto rentAmountRequestDto) {

        RentalPropertyEntity rentalPropertyEntity = rentalPropertyRepository.findById(id) .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien  " + id + " est introuvable"));
        rentalPropertyEntity.setRentAmount(rentAmountRequestDto.rentAmount());
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    @DeleteMapping("/rental-properties/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteRentalPropertyById(@PathVariable UUID id) {
        Optional<RentalPropertyEntity> rentalProperty = rentalPropertyRepository.findById(id);
        if(rentalProperty.isPresent())
            rentalPropertyRepository.delete(rentalProperty.get());
    }

}
