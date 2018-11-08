package com.thiago.locadora.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.locadora.entity.Aluguel;
import com.thiago.locadora.repository.AluguelRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/relatorio/gerencial/aluguel")
public class RelatorioGerencialController {
	
	@Autowired
	private AluguelRepository aluguelRepository;
	
	@GetMapping
    public void report(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
		List<Aluguel> locacoes = aluguelRepository.findAll();
		JasperReport jr = new JasperReport("relatorio-gerencial-alugueis.jasper", locacoes, response);
		jr.exportToPDF(response);
	}
	
	
	
	
	
	
}
