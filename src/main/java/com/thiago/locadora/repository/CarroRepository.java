package com.thiago.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.locadora.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
