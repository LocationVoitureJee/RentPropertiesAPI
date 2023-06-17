package com.RentProperties.RentProperties.Service;

import com.RentProperties.RentProperties.Model.RentalProperty;
import com.RentProperties.RentProperties.Model.dto.RentalPropertyDto;
import com.RentProperties.RentProperties.Repository.RentalPropertyRepository;
import com.RentProperties.RentProperties.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentProperties.RentProperties.Model.EnergyClassification;
import com.RentProperties.RentProperties.Model.PropertyType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RentalPropertyService {

    @Autowired
    private RentalPropertyRepository rentalPropertyRepository;

    // Convert RentalProperty entity to RentalPropertyDto
    private RentalPropertyDto mapToDto(RentalProperty rentalProperty) {
        return new RentalPropertyDto(
                rentalProperty.getId(),
                rentalProperty.getDescription(),
                rentalProperty.getTown(),
                rentalProperty.getAddress(),
                rentalProperty.getPropertyType(),
                rentalProperty.getRentAmount(),
                rentalProperty.getSecurityDepositAmount(),
                rentalProperty.getArea(),
                rentalProperty.getNumberOfBedrooms(),
                rentalProperty.getFloorNumber(),
                rentalProperty.getNumberOfFloors(),
                rentalProperty.getConstructionYear(),
                rentalProperty.getEnergyClassification(),
                rentalProperty.getHasElevator(),
                rentalProperty.getHasIntercom(),
                rentalProperty.getHasBalcony(),
                rentalProperty.getHasParkingSpace()
        );
    }

    // Convert RentalPropertyDto to RentalProperty entity
    private RentalProperty mapToEntity(RentalPropertyDto rentalPropertyDto) {
        RentalProperty rentalProperty = new RentalProperty();
        rentalProperty.setDescription(rentalPropertyDto.description());
        rentalProperty.setTown(rentalPropertyDto.town());
        rentalProperty.setAddress(rentalPropertyDto.address());
        rentalProperty.setPropertyType(PropertyType.valueOf(rentalPropertyDto.propertyType()));
        rentalProperty.setRentAmount(rentalPropertyDto.rentAmount());
        rentalProperty.setSecurityDepositAmount(rentalPropertyDto.securityDepositAmount());
        rentalProperty.setArea(rentalPropertyDto.area());
        rentalProperty.setNumberOfBedrooms(rentalPropertyDto.numberOfBedrooms());
        rentalProperty.setFloorNumber(rentalPropertyDto.floorNumber());
        rentalProperty.setNumberOfFloors(rentalPropertyDto.numberOfFloors());
        rentalProperty.setConstructionYear(rentalPropertyDto.constructionYear());
        rentalProperty.setEnergyClassification(EnergyClassification.valueOf(rentalPropertyDto.energyClassification()));
        rentalProperty.setHasElevator(rentalPropertyDto.hasElevator());
        rentalProperty.setHasIntercom(rentalPropertyDto.hasIntercom());
        rentalProperty.setHasBalcony(rentalPropertyDto.hasBalcony());
        rentalProperty.setHasParkingSpace(rentalPropertyDto.hasParkingSpace());
        return rentalProperty;
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
        RentalProperty rentalProperty = mapToEntity(rentalPropertyDto);
        rentalPropertyRepository.save(rentalProperty);
    }

    // Update rental property
    public void updateRentalProperty(Long id, RentalPropertyDto rentalPropertyDto) {
        RentalProperty rentalProperty = rentalPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental Property not found"));

        rentalProperty.setDescription(rentalPropertyDto.description());
        rentalProperty.setTown(rentalPropertyDto.town());
        rentalProperty.setAddress(rentalPropertyDto.address());
        rentalProperty.setPropertyType(PropertyType.valueOf(rentalPropertyDto.propertyType()));
        rentalProperty.setRentAmount(rentalPropertyDto.rentAmount());
        rentalProperty.setSecurityDepositAmount(rentalPropertyDto.securityDepositAmount());
        rentalProperty.setArea(rentalPropertyDto.area());
        rentalProperty.setNumberOfBedrooms(rentalPropertyDto.numberOfBedrooms());
        rentalProperty.setFloorNumber(rentalPropertyDto.floorNumber());
        rentalProperty.setNumberOfFloors(rentalPropertyDto.numberOfFloors());
        rentalProperty.setConstructionYear(rentalPropertyDto.constructionYear());
        rentalProperty.setEnergyClassification(EnergyClassification.valueOf(rentalPropertyDto.energyClassification()));
        rentalProperty.setHasElevator(rentalPropertyDto.hasElevator());
        rentalProperty.setHasIntercom(rentalPropertyDto.hasIntercom());
        rentalProperty.setHasBalcony(rentalPropertyDto.hasBalcony());
        rentalProperty.setHasParkingSpace(rentalPropertyDto.hasParkingSpace());

        rentalPropertyRepository.save(rentalProperty);
    }

    // Delete rental property by ID
    public void deleteRentalProperty(Long id) {
        rentalPropertyRepository.deleteById(id);
    }

    // not implement yet
    public void createRentalProperty(RentalProperty rentalProperty) {
    }


    // not implement yet
    public void partiallyUpdateRentalProperty(Long id, Map<String, Object> updates) {
    }
}