package com.thiago.locadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.locadora.entity.Aluguel;
import com.thiago.locadora.entity.Reserva;
import com.thiago.locadora.exceptions.BadRequestException;
import com.thiago.locadora.repository.AluguelRepository;
import com.thiago.locadora.repository.ReservaRepository;
import com.thiago.locadora.utils.DateUtils;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private AluguelRepository aluguelRepository;
	
	public Reserva reservar(Reserva reserva) {

		if ( !DateUtils.ehPeriodoValido(reserva.getDataInicio(), reserva.getDataFim()) ) {
			throw new BadRequestException();
		}
		
		List<Reserva> reservas = reservaRepository.findByCarroAndPeriodo(reserva.getCarro(), reserva.getDataInicio(), reserva.getDataFim());

		if ( !reservas.isEmpty() ) {
			throw new BadRequestException();
		}
		
		List<Aluguel> alugueis = aluguelRepository.findByCarroAndPeriodo(reserva.getCarro(), reserva.getDataInicio(), reserva.getDataFim());
		
		if ( !alugueis.isEmpty() ) {
			throw new BadRequestException();
		}
		
		return reservaRepository.save(reserva);
	}
	
	public Reserva cancelar(Long id, Reserva reserva) {
		Optional<Reserva> opt = reservaRepository.findById(id);

		if (opt.isPresent()) {
			Reserva r = opt.get();
			r.setCancelada(true);
			reservaRepository.save(r);
			return r;
		}
		
		throw new BadRequestException();
	}

}
