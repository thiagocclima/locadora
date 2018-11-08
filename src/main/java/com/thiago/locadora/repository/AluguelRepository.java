package com.thiago.locadora.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thiago.locadora.entity.Aluguel;
import com.thiago.locadora.entity.Carro;

public interface AluguelRepository extends JpaRepository<Aluguel, Long>{
	
	@Query(value="select a from Aluguel a where a.carro = :carro and a.devolvido = false "
			+ "and ( :dataInicio between a.dataInicio and a.dataFim "
			+ "or :dataFim between a.dataInicio and a.dataFim "
			+ "or a.dataInicio between :dataInicio and :dataFim "
			+ "or a.dataFim between :dataInicio and :dataFim ) ")
	List<Aluguel> findByCarroAndPeriodo(@Param("carro") Carro carro, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

}
