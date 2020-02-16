package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Smartwatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmartwatchRepository extends JpaRepository<Smartwatch, String> {

    Optional<Smartwatch> findByBrandAndModel(String brand, String model);
}
