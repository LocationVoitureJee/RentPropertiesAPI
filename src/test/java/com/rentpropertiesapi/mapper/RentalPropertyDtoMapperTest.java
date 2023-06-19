package com.rentpropertiesapi.mapper;

import com.rentpropertiesapi.dto.RentalPropertyRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyResponseDto;
import com.rentpropertiesapi.models.EnergyClassificationEntity;
import com.rentpropertiesapi.models.PropertyTypeEntity;
import com.rentpropertiesapi.models.RentalPropertyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RentalPropertyDtoMapperTest {

    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @BeforeEach
    void setUp() {
        rentalPropertyDtoMapper = new RentalPropertyDtoMapper();
    }

    @Test
    void testMapToDto() {
        // Setup
        PropertyTypeEntity mockPropertyType = Mockito.mock(PropertyTypeEntity.class);
        when(mockPropertyType.getDesignation()).thenReturn("Apartment");

        RentalPropertyEntity rentalProperty = new RentalPropertyEntity(
                "Test Description", "Test Town", "Test Address",
                mockPropertyType, 1000.0, 500.0, 100.0, 2, 3, 5,
                2000, null, true, false, true, true);

        // Execute
        RentalPropertyResponseDto dto = rentalPropertyDtoMapper.mapToDto(rentalProperty);

        // Assert
        assertEquals("Test Description", dto.description());
        assertEquals("Test Address", dto.address());
        assertEquals("Test Town", dto.town());
        assertEquals("Apartment", dto.propertyType());
        assertEquals(1000.0, dto.rentAmount());
        assertEquals(500.0, dto.securityDepositAmount());
        assertEquals(100.0, dto.area());
    }

    @Test
    void testMapToDtoList() {
        // Setup
        PropertyTypeEntity mockPropertyType = Mockito.mock(PropertyTypeEntity.class);
        when(mockPropertyType.getDesignation()).thenReturn("Apartment");

        RentalPropertyEntity rentalProperty = new RentalPropertyEntity(
                "Test Description", "Test Town", "Test Address",
                mockPropertyType, 1000.0, 500.0, 100.0, 2, 3, 5,
                2000, null, true, false, true, true);

        List<RentalPropertyEntity> rentalProperties = Collections.singletonList(rentalProperty);

        // Execute
        List<RentalPropertyResponseDto> dtoList = rentalPropertyDtoMapper.mapToDtoList(rentalProperties);

        // Assert
        assertEquals(1, dtoList.size());

        RentalPropertyResponseDto dto = dtoList.get(0);
        assertEquals("Test Description", dto.description());
        assertEquals("Test Address", dto.address());
        assertEquals("Test Town", dto.town());
        assertEquals("Apartment", dto.propertyType());
        assertEquals(1000.0, dto.rentAmount());
        assertEquals(500.0, dto.securityDepositAmount());
        assertEquals(100.0, dto.area());
    }

    @Test
    void testMapToEntity() {
        // Setup
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity();
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity();

        RentalPropertyRequestDto rentalPropertyRequestDto = RentalPropertyRequestDto.builder()
                .description("Description")
                .town("Town")
                .address("Address")
                .propertyType("Apartment")
                .rentAmount(1000.0)
                .securityDepositAmount(500.0)
                .area(100.0)
                .bedroomsCount(2)
                .floorNumber(3)
                .numberOfFloors(5)
                .constructionYear(2000)
                .energyClassification("A")
                .hasElevator(true)
                .hasIntercom(false)
                .hasBalcony(true)
                .hasParkingSpace(true)
                .build();

        // Execute
        RentalPropertyEntity entity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto, propertyTypeEntity, energyClassificationEntity);

        // Assert
        assertEquals("Description", entity.getDescription());
        assertEquals("Town", entity.getTown());
        assertEquals("Address", entity.getAddress());
        assertEquals(propertyTypeEntity, entity.getPropertyType());
        assertEquals(1000.0, entity.getRentAmount());
        assertEquals(500.0, entity.getSecurityDepositAmount());
        assertEquals(100.0, entity.getArea());
        assertEquals(3, entity.getFloorNumber());
        assertEquals(5, entity.getNumberOfFloors());
        assertEquals(2000, entity.getConstructionYear());
        assertEquals(energyClassificationEntity, entity.getEnergyClassification());
        assertEquals(true, entity.isHasElevator());
        assertEquals(false, entity.isHasIntercom());
        assertEquals(true, entity.isHasBalcony());
        assertEquals(true, entity.isHasParkingSpace());
    }
}
