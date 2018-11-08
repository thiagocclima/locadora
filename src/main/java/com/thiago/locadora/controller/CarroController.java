package com.thiago.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.locadora.entity.Carro;
import com.thiago.locadora.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Carro> salvar(@RequestBody Carro carro) {
		return new ResponseEntity<Carro>(carroService.salvar(carro), HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}", produces = "application/json")
	public ResponseEntity<Carro> obterCarro(@PathVariable(value="id") Long id) {
		Carro carro = carroService.obterCarro(id);
		return new ResponseEntity<Carro>( carro, HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Carro> alterar(@PathVariable("id") Long id, @RequestBody Carro carro) {
		return new ResponseEntity<Carro>(carroService.salvar(carro), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public ResponseEntity<Void> remover(@PathVariable(value="id") Long id) {
		carroService.remover(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Carro>> listar() {
		List<Carro> carros = carroService.listar();
		HttpStatus httpStatus = carros.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK ;
		return new ResponseEntity<List<Carro>>( carros, httpStatus);
	}

}
