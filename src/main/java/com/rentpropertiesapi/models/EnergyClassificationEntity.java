package com.rentpropertiesapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Table(name = "energy_classification")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnergyClassificationEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "designation")
    private String designation;
}
