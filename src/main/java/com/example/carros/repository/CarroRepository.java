
package com.example.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carros.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
