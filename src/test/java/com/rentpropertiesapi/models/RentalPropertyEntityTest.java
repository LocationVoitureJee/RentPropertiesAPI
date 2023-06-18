package com.rentpropertiesapi.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class RentalPropertyEntityTest {

    @Test
    void shouldCreateWithFullConstructor() {
        // Given
        UUID expectedId = UUID.randomUUID();
        String expectedDescription = "Beautiful Apartment";
        String expectedTown = "Paris";
        String expectedAddress = "123 Champs Elysees";
        PropertyTypeEntity expectedPropertyType = mock(PropertyTypeEntity.class);
        double expectedRentAmount = 1200.00;
        double expectedSecurityDepositAmount = 2400.00;
        double expectedArea = 80.00;
        int expectedNumberOfBedrooms = 2;
        int expectedFloorNumber = 3;
        int expectedNumberOfFloors = 6;
        int expectedConstructionYear = 2005;
        EnergyClassificationEntity expectedEnergyClassification = mock(EnergyClassificationEntity.class);
        boolean expectedHasElevator = true;
        boolean expectedHasIntercom = true;
        boolean expectedHasBalcony = true;
        boolean expectedHasParkingSpace = false;

        RentalPropertyEntity rentalPropertyEntity = new RentalPropertyEntity(expectedDescription,
                expectedTown, expectedAddress, expectedPropertyType, expectedRentAmount,
                expectedSecurityDepositAmount, expectedArea, expectedNumberOfBedrooms,
                expectedFloorNumber, expectedNumberOfFloors, expectedConstructionYear,
                expectedEnergyClassification, expectedHasElevator, expectedHasIntercom,
                expectedHasBalcony, expectedHasParkingSpace);

        assertThat(rentalPropertyEntity.getDescription()).isEqualTo(expectedDescription);
        assertThat(rentalPropertyEntity.getTown()).isEqualTo(expectedTown);
        assertThat(rentalPropertyEntity.getAddress()).isEqualTo(expectedAddress);
        assertThat(rentalPropertyEntity.getPropertyType()).isEqualTo(expectedPropertyType);
        assertThat(rentalPropertyEntity.getRentAmount()).isEqualTo(expectedRentAmount);
        assertThat(rentalPropertyEntity.getSecurityDepositAmount()).isEqualTo(expectedSecurityDepositAmount);
        assertThat(rentalPropertyEntity.getArea()).isEqualTo(expectedArea);
        assertThat(rentalPropertyEntity.getNumberOfBedrooms()).isEqualTo(expectedNumberOfBedrooms);
        assertThat(rentalPropertyEntity.getFloorNumber()).isEqualTo(expectedFloorNumber);
        assertThat(rentalPropertyEntity.getNumberOfFloors()).isEqualTo(expectedNumberOfFloors);
        assertThat(rentalPropertyEntity.getConstructionYear()).isEqualTo(expectedConstructionYear);
        assertThat(rentalPropertyEntity.getEnergyClassification()).isEqualTo(expectedEnergyClassification);
        assertThat(rentalPropertyEntity.isHasElevator()).isEqualTo(expectedHasElevator);
        assertThat(rentalPropertyEntity.isHasIntercom()).isEqualTo(expectedHasIntercom);
        assertThat(rentalPropertyEntity.isHasBalcony()).isEqualTo(expectedHasBalcony);
        assertThat(rentalPropertyEntity.isHasParkingSpace()).isEqualTo(expectedHasParkingSpace);
    }

    @Test
    void shouldSetAndGetProperties() {
        // Given
        RentalPropertyEntity rentalPropertyEntity = new RentalPropertyEntity();

        UUID expectedId = UUID.randomUUID();
        rentalPropertyEntity.setId(expectedId);

        assertThat(rentalPropertyEntity.getId()).isEqualTo(expectedId);
    }
}

