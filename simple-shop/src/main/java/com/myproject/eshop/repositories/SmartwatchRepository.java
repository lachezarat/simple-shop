package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Smartwatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartwatchRepository extends JpaRepository<Smartwatch, String> {

}
