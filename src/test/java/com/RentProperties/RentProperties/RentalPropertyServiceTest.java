package com.RentProperties.RentProperties;


import com.RentProperties.RentProperties.Model.RentalProperty;
import com.RentProperties.RentProperties.Model.dto.RentalPropertyDto;
import com.RentProperties.RentProperties.Repository.RentalPropertyRepository;
import com.RentProperties.RentProperties.Service.RentalPropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RentalPropertyServiceTest {

	@InjectMocks
	private RentalPropertyService rentalPropertyService;

	@Mock
	private RentalPropertyRepository rentalPropertyRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllRentalProperties() {
		List<RentalProperty> rentalProperties = new ArrayList<>();
		RentalProperty rentalProperty = new RentalProperty();
		rentalProperty.setDescription("Test property");
		rentalProperties.add(rentalProperty);

		when(rentalPropertyRepository.findAll()).thenReturn(rentalProperties);

		List<RentalPropertyDto> result = rentalPropertyService.getAllRentalProperties();
		assertEquals(1, result.size());
		assertEquals("Test property", result.get(0).description());
	}

	@Test
	public void testGetRentalPropertyById() {
		RentalProperty rentalProperty = new RentalProperty();
		rentalProperty.setDescription("Test property");

		when(rentalPropertyRepository.findById(1L)).thenReturn(Optional.of(rentalProperty));

		RentalPropertyDto result = rentalPropertyService.getRentalPropertyById(1L);
		assertEquals("Test property", result.description());
	}

	@Test
	public void testSaveRentalProperty() {
		RentalPropertyDto rentalPropertyDto = new RentalPropertyDto(
				1L,
				"description",
				"town",
				"address",
				"FLAT",
				1200.0,
				2000.0,
				50.0,
				2,
				3,
				4,
				"2000",
				"A",
				true,
				true,
				true,
				true
		);

		rentalPropertyService.saveRentalProperty(rentalPropertyDto);

		verify(rentalPropertyRepository, times(1)).save(any(RentalProperty.class));
	}

	
}