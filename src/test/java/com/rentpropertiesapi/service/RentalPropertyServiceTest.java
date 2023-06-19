package com.rentpropertiesapi.service;

import com.rentpropertiesapi.dto.RentalPropertyRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyResponseDto;
import com.rentpropertiesapi.exception.NotFoundEnergyClassificationException;
import com.rentpropertiesapi.exception.NotFoundPropertyTypeException;
import com.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import com.rentpropertiesapi.models.EnergyClassificationEntity;
import com.rentpropertiesapi.models.PropertyTypeEntity;
import com.rentpropertiesapi.models.RentalPropertyEntity;
import com.rentpropertiesapi.repository.EnergyClassificationRepository;
import com.rentpropertiesapi.repository.PropertyTypeRepository;
import com.rentpropertiesapi.repository.RentalPropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalPropertyServiceTest {

    @Mock
    private RentalPropertyRepository rentalPropertyRepository;

    @Mock
    private EnergyClassificationRepository energyClassificationRepository;

    @Mock
    private PropertyTypeRepository propertyTypeRepository;

    @Mock
    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @InjectMocks
    private RentalPropertyService rentalPropertyService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetRentalProperties() {
        RentalPropertyEntity rentalPropertyEntity = mock(RentalPropertyEntity.class);
        RentalPropertyResponseDto rentalPropertyResponseDto = mock(RentalPropertyResponseDto.class);

        when(rentalPropertyRepository.findAll()).thenReturn(Collections.singletonList(rentalPropertyEntity));
        when(rentalPropertyDtoMapper.mapToDtoList(any())).thenReturn(Collections.singletonList(rentalPropertyResponseDto));

        List<RentalPropertyResponseDto> result = rentalPropertyService.getRentalProperties();

        assertFalse(result.isEmpty());
        assertEquals(rentalPropertyResponseDto, result.get(0));
    }

    @Test
    void testCreateRentalPropertyValidData() {
        RentalPropertyRequestDto requestDto = mock(RentalPropertyRequestDto.class);
        PropertyTypeEntity propertyType = mock(PropertyTypeEntity.class);
        EnergyClassificationEntity energyClassification = mock(EnergyClassificationEntity.class);
        RentalPropertyEntity rentalPropertyEntity = mock(RentalPropertyEntity.class);

        when(requestDto.propertyType()).thenReturn("Apartment");
        when(requestDto.energyClassification()).thenReturn("A");
        when(propertyTypeRepository.findByDesignation("Apartment")).thenReturn(Optional.of(propertyType));
        when(energyClassificationRepository.findByDesignation("A")).thenReturn(Optional.of(energyClassification));
        when(rentalPropertyDtoMapper.mapToEntity(any(), any(), any())).thenReturn(rentalPropertyEntity);

        rentalPropertyService.createRentalProperty(requestDto);

        verify(rentalPropertyRepository).save(any(RentalPropertyEntity.class));
    }

    @Test
    void testCreateRentalPropertyInvalidPropertyType() {
        RentalPropertyRequestDto requestDto = mock(RentalPropertyRequestDto.class);

        when(requestDto.propertyType()).thenReturn("InvalidType");

        assertThrows(NotFoundPropertyTypeException.class, () -> rentalPropertyService.createRentalProperty(requestDto));
    }

    @Test
    void testCreateRentalPropertyInvalidEnergyClassification() {
        RentalPropertyRequestDto requestDto = mock(RentalPropertyRequestDto.class);
        PropertyTypeEntity propertyType = mock(PropertyTypeEntity.class);

        when(requestDto.propertyType()).thenReturn("Apartment");
        when(requestDto.energyClassification()).thenReturn("InvalidClassification");
        when(propertyTypeRepository.findByDesignation("Apartment")).thenReturn(Optional.of(propertyType));

        assertThrows(NotFoundEnergyClassificationException.class, () -> rentalPropertyService.createRentalProperty(requestDto));
    }
}
