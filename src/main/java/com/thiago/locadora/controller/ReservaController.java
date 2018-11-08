package com.thiago.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.locadora.entity.Reserva;
import com.thiago.locadora.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Reserva> reservar(@RequestBody Reserva reserva) {
		Reserva novaReserva = reservaService.reservar(reserva);
		return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "cancelar/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Reserva> cancelar(@PathVariable("id") Long id, @RequestBody Reserva reserva) {
		Reserva r = reservaService.cancelar(id, reserva);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

}
