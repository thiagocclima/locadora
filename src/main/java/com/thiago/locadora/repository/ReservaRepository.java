package com.thiago.locadora.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thiago.locadora.entity.Carro;
import com.thiago.locadora.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	
	@Query(value="select r from Reserva r where r.carro = :carro and r.cancelada = false "
			+ "and ( :dataInicio between r.dataInicio and r.dataFim "
			+ "or :dataFim between r.dataInicio and r.dataFim "
			+ "or r.dataInicio between :dataInicio and :dataFim "
			+ "or r.dataFim between :dataInicio and :dataFim ) ")
	List<Reserva> findByCarroAndPeriodo(@Param("carro") Carro carro, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

}
