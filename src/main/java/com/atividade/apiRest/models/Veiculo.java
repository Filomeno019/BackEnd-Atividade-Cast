package com.atividade.apiRest.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "VEICULO")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private BigDecimal valor;

	@ManyToOne
	private Modelo modelo;
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}  

	public void setId(Integer id) {
		this.id = id;
	}


	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Veiculo() {
		super();
	}

	public Veiculo(Integer id, BigDecimal valor, Modelo modelo) {
		super();
		this.id = id;
		this.valor = valor;
		this.modelo = modelo;
	}

	public Veiculo(Veiculo veiculo) {
		
	}

}
