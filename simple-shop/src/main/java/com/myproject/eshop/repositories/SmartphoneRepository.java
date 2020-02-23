package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, String> {

    Optional<Smartphone> findByBrandAndModel(String brand, String model);

    void delete(Smartphone smartphone);
}
