package com.RentProperties.RentProperties.Controller;

import com.RentProperties.RentProperties.Model.RentalProperty;
import com.RentProperties.RentProperties.Service.RentalPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rent-properties-api/rental-properties")
public class RentalPropertyController {

    @Autowired
    private RentalPropertyService rentalPropertyService;

    @GetMapping
    public ResponseEntity<List<RentalProperty>> getAllRentalProperties() {
        List<RentalProperty> rentalProperties = rentalPropertyService.getAllRentalProperties();
        return ResponseEntity.ok(rentalProperties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalProperty> getRentalPropertyById(@PathVariable Long id) {
        RentalProperty rentalProperty = rentalPropertyService.getRentalPropertyById(id);
        return ResponseEntity.ok(rentalProperty);
    }

    @PostMapping
    public ResponseEntity<Void> createRentalProperty(@RequestBody RentalProperty rentalProperty) {
        rentalPropertyService.createRentalProperty(rentalProperty);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRentalProperty(@PathVariable Long id, @RequestBody RentalProperty rentalProperty) {
        rentalPropertyService.updateRentalProperty(id, rentalProperty);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partiallyUpdateRentalProperty(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        rentalPropertyService.partiallyUpdateRentalProperty(id, updates);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentalProperty(@PathVariable Long id) {
        rentalPropertyService.deleteRentalProperty(id);
        return ResponseEntity.noContent().build();
    }
}