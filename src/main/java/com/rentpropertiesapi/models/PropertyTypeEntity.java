package com.rentpropertiesapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Table(name = "property_type")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyTypeEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "designation")
    private String designation;
}
