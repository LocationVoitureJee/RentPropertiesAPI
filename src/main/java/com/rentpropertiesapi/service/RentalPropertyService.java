package com.rentpropertiesapi.service;

import com.rentpropertiesapi.dto.RentAmountRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyResponseDto;
import com.rentpropertiesapi.exception.NotFoundEnergyClassificationException;
import com.rentpropertiesapi.exception.NotFoundPropertyTypeException;
import com.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import com.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import com.rentpropertiesapi.models.EnergyClassificationEntity;
import com.rentpropertiesapi.models.PropertyTypeEntity;
import com.rentpropertiesapi.models.RentalPropertyEntity;
import com.rentpropertiesapi.repository.EnergyClassificationRepository;
import com.rentpropertiesapi.repository.PropertyTypeRepository;
import com.rentpropertiesapi.repository.RentalPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentalPropertyService {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final EnergyClassificationRepository energyClassificationRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    public RentalPropertyService(RentalPropertyRepository rentalPropertyRepository,
                                 EnergyClassificationRepository energyClassificationRepository,
                                 PropertyTypeRepository propertyTypeRepository,
                                 RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.energyClassificationRepository = energyClassificationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    public List<RentalPropertyResponseDto> getRentalProperties() {
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();
        return rentalPropertyDtoMapper.mapToDtoList(rentalProperties);
    }

    public RentalPropertyResponseDto getRentalPropertyById(String id) {
        return rentalPropertyRepository.findById(UUID.fromString(id))
                .map(rentalPropertyDtoMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier " + id + " est introuvable"));
    }

    public void createRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto) {

        PropertyTypeEntity propertyType = propertyTypeRepository.findByDesignation(rentalPropertyRequestDto.propertyType())
                .orElseThrow(() -> new NotFoundPropertyTypeException("Le property type " + rentalPropertyRequestDto.propertyType() + " est introuvable"));

        EnergyClassificationEntity energyClassification = energyClassificationRepository.findByDesignation(rentalPropertyRequestDto.energyClassification())
                .orElseThrow(() -> new NotFoundEnergyClassificationException("Le energy type " + rentalPropertyRequestDto.energyClassification() + " est introuvable"));

        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto, propertyType, energyClassification);

        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    public void updateRentalProperty(UUID id, RentalPropertyRequestDto rentalPropertyRequestDto) {

        RentalPropertyEntity rentalPropertyEntityOld = rentalPropertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien " + id + " est introuvable"));

        PropertyTypeEntity propertyType = propertyTypeRepository.findByDesignation(rentalPropertyRequestDto.propertyType())
                .orElseThrow(() -> new NotFoundPropertyTypeException("Le property type " + rentalPropertyRequestDto.propertyType() + " est introuvable"));

        EnergyClassificationEntity energyClassification = energyClassificationRepository.findByDesignation(rentalPropertyRequestDto.energyClassification())
                .orElseThrow(() -> new NotFoundEnergyClassificationException("Le energy type " + rentalPropertyRequestDto.energyClassification() + " est introuvable"));

        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto, propertyType, energyClassification);
        rentalPropertyEntity.setId(rentalPropertyEntityOld.getId());
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    public void updateRentalPropertyAmount(UUID id, RentAmountRequestDto rentAmountRequestDto) {

        RentalPropertyEntity rentalPropertyEntity = rentalPropertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien " + id + " est introuvable"));
        rentalPropertyEntity.setRentAmount(rentAmountRequestDto.rentAmount());
        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    public void deleteRentalPropertyById(UUID id) {
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier " + id + " est introuvable"));

        rentalPropertyRepository.delete(rentalPropertyEntity);
    }
}
