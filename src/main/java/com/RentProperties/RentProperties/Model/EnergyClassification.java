package com.RentProperties.RentProperties.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "energy_classification")
@Data // Lombok annotation
public class EnergyClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation", nullable = false, length = 1)
    private String designation;
}

