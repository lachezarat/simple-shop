package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {

}