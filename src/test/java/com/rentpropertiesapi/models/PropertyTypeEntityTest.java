package com.rentpropertiesapi.models;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class PropertyTypeEntityTest {

    @Test
    void shouldCreate() {
        // Given
        UUID expectedId = UUID.randomUUID();
        String expectedDesignation = "Residential";

        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(expectedId, expectedDesignation);

        assertThat(propertyTypeEntity.getId()).isEqualTo(expectedId);
        assertThat(propertyTypeEntity.getDesignation()).isEqualTo(expectedDesignation);
    }

    @Test
    void shouldGetProperties() {
        // Given
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity();

        UUID expectedId = UUID.randomUUID();
        String expectedDesignation = "Commercial";

        org.springframework.test.util.ReflectionTestUtils.setField(propertyTypeEntity, "id", expectedId);
        org.springframework.test.util.ReflectionTestUtils.setField(propertyTypeEntity, "designation", expectedDesignation);

        assertThat(propertyTypeEntity.getId()).isEqualTo(expectedId);
        assertThat(propertyTypeEntity.getDesignation()).isEqualTo(expectedDesignation);
    }
}

