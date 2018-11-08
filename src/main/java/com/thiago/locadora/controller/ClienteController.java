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

import com.thiago.locadora.entity.Cliente;
import com.thiago.locadora.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(clienteService.salvar(cliente), HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}", produces = "application/json")
	public ResponseEntity<Cliente> obterCliente(@PathVariable(value="id") Long id) {
		Cliente cliente = clienteService.obterCliente(id);
		return new ResponseEntity<Cliente>( cliente, HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cliente> alterar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(clienteService.salvar(cliente), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public ResponseEntity<Void> remover(@PathVariable(value="id") Long id) {
		clienteService.remover(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = clienteService.listar();
		HttpStatus httpStatus = clientes.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK ;
		return new ResponseEntity<List<Cliente>>( clientes, httpStatus);
	}

}
