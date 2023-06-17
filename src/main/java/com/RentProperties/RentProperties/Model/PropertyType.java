package com.RentProperties.RentProperties.Model;

import com.RentProperties.RentProperties.Model.dto.PropertyTypeDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "property_type")
@Data // Lombok annotation
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation", nullable = false, length = 5)
    private String designation;

    public static PropertyType valueOf(PropertyTypeDto dto) {
        if (dto == null) {
            return null;
        }

        PropertyType propertyType = new PropertyType();
        propertyType.setId(dto.id());
        propertyType.setDesignation(dto.type());

        return propertyType;
    }
}
