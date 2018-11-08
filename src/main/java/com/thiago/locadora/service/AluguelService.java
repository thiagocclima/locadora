package com.thiago.locadora.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.locadora.entity.Aluguel;
import com.thiago.locadora.entity.Carro;
import com.thiago.locadora.entity.Reserva;
import com.thiago.locadora.exceptions.BadRequestException;
import com.thiago.locadora.repository.AluguelRepository;
import com.thiago.locadora.repository.CarroRepository;
import com.thiago.locadora.repository.ReservaRepository;
import com.thiago.locadora.utils.DateUtils;

@Service
public class AluguelService {
	
	@Autowired
	private AluguelRepository aluguelRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private CarroRepository carroRepository;
	
	public Aluguel alugar( Aluguel aluguel) {
		if ( !DateUtils.ehPeriodoValido(aluguel.getDataInicio(), aluguel.getDataFim()) ) {
			throw new BadRequestException();
		}
		
		List<Reserva> reservas = reservaRepository.findByCarroAndPeriodo(aluguel.getCarro(), aluguel.getDataInicio(), aluguel.getDataFim());

		if ( !reservas.isEmpty() ) {
			throw new BadRequestException();
		}
		
		List<Aluguel> alugueis = aluguelRepository.findByCarroAndPeriodo(aluguel.getCarro(), aluguel.getDataInicio(), aluguel.getDataFim());
		
		if ( !alugueis.isEmpty() ) {
			throw new BadRequestException();
		}
		
		Optional<Carro> opt = carroRepository.findById(aluguel.getCarro().getId());
		if ( !opt.isPresent() ) {
			throw new BadRequestException();
		}
		
		Carro carro = opt.get();
		aluguel.setValor(obterValorLocacao(aluguel.getDataInicio(), aluguel.getDataFim(), carro));
		return aluguelRepository.save(aluguel);
	}
	
	public Aluguel alugar ( Reserva reserva ) {
		Optional<Carro> opt = carroRepository.findById(reserva.getCarro().getId());
		if ( !opt.isPresent() ) {
			throw new BadRequestException();
		}
		
		Carro carro = opt.get();
		
		Aluguel aluguel = new Aluguel(reserva);
		aluguel.setValor(obterValorLocacao(aluguel.getDataInicio(), aluguel.getDataFim(), carro));
		
		return aluguelRepository.save(aluguel);
	}
	
	public Aluguel devolver ( Long id, Aluguel devolucao ) {
		Optional<Aluguel> opt = aluguelRepository.findById(id);
		
		if ( !opt.isPresent() ) {
			throw new BadRequestException();
		}
		
	    Aluguel aluguel = opt.get();
	    aluguel.setDevolvido(true);
	    aluguel.setDataFim(devolucao.getDataFim());
	    aluguel.setValor(obterValorLocacao(aluguel.getDataInicio(), aluguel.getDataFim(), aluguel.getCarro()));
		
		return aluguelRepository.save(aluguel);
	}
	
	private BigDecimal obterValorLocacao(Date dataInicio, Date dataFim, Carro carro) {
		long diffInMillies = Math.abs(dataFim.getTime() - dataInicio.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
	    BigDecimal valor = carro.getValorDiaria().multiply(BigDecimal.valueOf(diff));
		
		return valor;
	}

}
