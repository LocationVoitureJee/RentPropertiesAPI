package com.rentpropertiesapi.models;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class EnergyClassificationEntityTest {

    @Test
    void shouldCreate() {
        // Given
        UUID expectedId = UUID.randomUUID();
        String expectedDesignation = "High Efficiency";

        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity(expectedId, expectedDesignation);

        assertThat(energyClassificationEntity.getId()).isEqualTo(expectedId);
        assertThat(energyClassificationEntity.getDesignation()).isEqualTo(expectedDesignation);
    }

    @Test
    void shouldGetProperties() {
        // Given
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity();

        UUID expectedId = UUID.randomUUID();
        String expectedDesignation = "Renewable";

        org.springframework.test.util.ReflectionTestUtils.setField(energyClassificationEntity, "id", expectedId);
        org.springframework.test.util.ReflectionTestUtils.setField(energyClassificationEntity, "designation", expectedDesignation);

        assertThat(energyClassificationEntity.getId()).isEqualTo(expectedId);
        assertThat(energyClassificationEntity.getDesignation()).isEqualTo(expectedDesignation);
    }
}

