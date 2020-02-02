package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, String> {

}
