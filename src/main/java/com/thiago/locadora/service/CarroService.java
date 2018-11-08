package com.thiago.locadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.locadora.entity.Carro;
import com.thiago.locadora.exceptions.ResourceNotFoundException;
import com.thiago.locadora.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	public Carro salvar(Carro carro) {
		return carroRepository.save(carro);
	}
	
	public Carro obterCarro(Long id) {
		Optional<Carro> opt = carroRepository.findById(id);
		
		if ( !opt.isPresent()) {
			throw new ResourceNotFoundException();
		}
		
		return opt.get();
	}
	
	public Carro alterar(Long id, Carro carro) {
		return carroRepository.save(carro);
	}
	
	public void remover(Long id) {
		Optional<Carro> opt = carroRepository.findById(id);
		
		if ( !opt.isPresent() ) {
			throw new ResourceNotFoundException();
		}
		
		carroRepository.delete(opt.get());
	}
	
	public List<Carro> listar() {
		return carroRepository.findAll();
	}

}
