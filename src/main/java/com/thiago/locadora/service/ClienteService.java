package com.thiago.locadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.locadora.entity.Cliente;
import com.thiago.locadora.exceptions.ResourceNotFoundException;
import com.thiago.locadora.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente obterCliente(Long id) {
		Optional<Cliente> opt = clienteRepository.findById(id);
		
		if ( !opt.isPresent() ) {
			throw new ResourceNotFoundException();
		}
		
		return opt.get();
	}
	
	public Cliente alterar(Long id, Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void remover(Long id) {
		Optional<Cliente> opt = clienteRepository.findById(id);
		
		if (opt.isPresent()) {
			throw new ResourceNotFoundException();
		}
		
		clienteRepository.delete(opt.get());
	}
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

}
