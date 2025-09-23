package com.marcos.amidogbackend.repository;

import com.marcos.amidogbackend.model.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerroRepository extends JpaRepository<Perro, Long> {
}
