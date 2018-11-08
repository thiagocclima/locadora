package com.thiago.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.locadora.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
