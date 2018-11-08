package com.thiago.locadora.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ALUGUEL")
public class Aluguel {
	
	@Id
	@Column(name="ID_ALUGUEL")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DATA_INICIO", nullable=false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	
	@Column(name="DATA_FIM", nullable=false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE", nullable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="ID_CARRO", nullable=false)
	private Carro carro;
	
	@Column(name="VALOR")
	private BigDecimal valor;
	
	@OneToOne
	@JoinColumn(name="ID_RESERVA")
	private Reserva reserva;
	
	@Column(name="IS_DEVOLVIDO")
	private Boolean devolvido = new Boolean(false);
	
	public Aluguel() {}
	
	public Aluguel(Reserva reserva) {
		this.carro = reserva.getCarro();
		this.cliente = reserva.getCliente();
		this.dataInicio = reserva.getDataInicio();
		this.dataFim = reserva.getDataFim();
		this.reserva = reserva;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Boolean getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(Boolean devolvido) {
		this.devolvido = devolvido;
	}

}
