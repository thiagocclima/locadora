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

import com.thiago.locadora.entity.Aluguel;
import com.thiago.locadora.entity.Reserva;
import com.thiago.locadora.service.AluguelService;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
	
	@Autowired
	private AluguelService aluguelService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Aluguel> reservar(@RequestBody Aluguel aluguel) {
		Aluguel novoAluguel = aluguelService.alugar(aluguel);
		return new ResponseEntity<>(novoAluguel, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "reserva", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Aluguel> reservar(@RequestBody Reserva reserva) {
		Aluguel novoAluguel = aluguelService.alugar(reserva);
		return new ResponseEntity<>(novoAluguel, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "devolver/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Aluguel> cancelar(@PathVariable("id") Long id, @RequestBody Aluguel aluguel) {
		Aluguel devolvido = aluguelService.devolver(id, aluguel);
		return new ResponseEntity<Aluguel>(devolvido, HttpStatus.OK);
	}

}
