package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, String> {

    Optional<Television> findByBrandAndModel(String brand, String model);

    void delete(Television television);
}
