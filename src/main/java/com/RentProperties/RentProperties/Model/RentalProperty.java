package com.RentProperties.RentProperties.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rental_property")
@Data // Lombok annotation to generate getters, setters, equals, hashCode and toString methods
public class RentalProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "town", nullable = false, length = 100)
    private String town;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyType propertyType;

    @Column(name = "rent_amount", nullable = false)
    private Double rentAmount;

    @Column(name = "security_deposit_amount", nullable = false)
    private Double securityDepositAmount;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "number_of_bedrooms")
    private Integer numberOfBedrooms;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "number_of_floors")
    private Integer numberOfFloors;

    @Column(name = "construction_year", length = 4)
    private String constructionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "energy_classification_id")
    private EnergyClassification energyClassification;

    @Column(name = "has_elevator")
    private Boolean hasElevator;

    @Column(name = "has_intercom")
    private Boolean hasIntercom;

    @Column(name = "has_balcony")
    private Boolean hasBalcony;

    @Column(name = "has_parking_space")
    private Boolean hasParkingSpace;
}
