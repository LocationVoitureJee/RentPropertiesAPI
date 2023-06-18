package com.rentpropertiesapi.repository;

import com.rentpropertiesapi.models.RentalPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RentalPropertyRepository extends JpaRepository<RentalPropertyEntity, UUID> {
 }