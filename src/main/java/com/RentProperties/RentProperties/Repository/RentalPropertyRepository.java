package com.RentProperties.RentProperties.Repository;

import com.RentProperties.RentProperties.Model.RentalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalPropertyRepository extends JpaRepository<RentalProperty, Long> {


}

