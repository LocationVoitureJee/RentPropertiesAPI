package com.RentProperties.RentProperties.Model;

import com.RentProperties.RentProperties.Model.dto.EnergyClassificationDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "energy_classification")
@Data // Lombok annotation
public class EnergyClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation", nullable = false, length = 1)
    private String designation;

    public static EnergyClassification valueOf(EnergyClassificationDto dto) {
        if (dto == null) {
            return null;
        }

        EnergyClassification energyClassification = new EnergyClassification();
        energyClassification.setId(dto.id());
        energyClassification.setDesignation(dto.classification());

        return energyClassification;
    }
}

