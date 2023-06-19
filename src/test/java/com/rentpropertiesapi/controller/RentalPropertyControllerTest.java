package com.rentpropertiesapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentpropertiesapi.controller.RentalPropertyController;
import com.rentpropertiesapi.dto.RentAmountRequestDto;
import com.rentpropertiesapi.dto.RentalPropertyRequestDto;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RentalPropertyController.class)
class RentalPropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalPropertyRepository rentalPropertyRepository;

    @MockBean
    private EnergyClassificationRepository energyClassificationRepository;

    @MockBean
    private PropertyTypeRepository propertyTypeRepository;

    @MockBean
    private RentalPropertyDtoMapper rentalPropertyDtoMapper;



}
