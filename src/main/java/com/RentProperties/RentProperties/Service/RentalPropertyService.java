package com.RentProperties.RentProperties.Service;

import com.RentProperties.RentProperties.Model.RentalProperty;
import com.RentProperties.RentProperties.Model.dto.RentalPropertyDto;
import com.RentProperties.RentProperties.Repository.RentalPropertyRepository;
import com.RentProperties.RentProperties.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalPropertyService {

    @Autowired
    private RentalPropertyRepository rentalPropertyRepository;

    // Convert RentalProperty entity to RentalPropertyDto
    private RentalPropertyDto mapToDto(RentalProperty rentalProperty) {
        RentalPropertyDto dto = new RentalPropertyDto();
        dto.setId(rentalProperty.getId());
        dto.setDescription(rentalProperty.getDescription());
        dto.setTown(rentalProperty.getTown());
        dto.setAddress(rentalProperty.getAddress());
        // ... (map the rest of the fields)
        return dto;
    }

    // Convert RentalPropertyDto to RentalProperty entity
    private RentalProperty mapToEntity(RentalPropertyDto rentalPropertyDto) {
        RentalProperty entity = new RentalProperty();
        entity.setDescription(rentalPropertyDto.getDescription());
        entity.setTown(rentalPropertyDto.getTown());
        entity.setAddress(rentalPropertyDto.getAddress());
        // ... (map the rest of the fields)
        return entity;
    }

    // Get all rental properties
    public List<RentalPropertyDto> getAllRentalProperties() {
        return rentalPropertyRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get rental property by ID
    public RentalPropertyDto getRentalPropertyById(Long id) {
        return rentalPropertyRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Rental Property not found"));
    }

    // Save rental property
    public void saveRentalProperty(RentalPropertyDto rentalPropertyDto) {
        RentalProperty entity = mapToEntity(rentalPropertyDto);
        rentalPropertyRepository.save(entity);
    }

    // Update rental property
    public void updateRentalProperty(Long id, RentalPropertyDto rentalPropertyDto) {
        RentalProperty entity = rentalPropertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental Property not found"));
        entity.setDescription(rentalPropertyDto.getDescription());
        entity.setTown(rentalPropertyDto.getTown());
        entity.setAddress(rentalPropertyDto.getAddress());
        // ... (map the rest of the fields)
        rentalPropertyRepository.save(entity);
    }

    // Delete rental property by ID
    public void deleteRentalProperty(Long id) {
        rentalPropertyRepository.deleteById(id);
    }
}