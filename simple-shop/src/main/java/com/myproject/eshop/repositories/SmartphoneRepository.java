package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, String> {

}
