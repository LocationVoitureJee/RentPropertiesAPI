package com.RentProperties.RentProperties.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "property_type")
@Data // Lombok annotation
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation", nullable = false, length = 5)
    private String designation;
}
