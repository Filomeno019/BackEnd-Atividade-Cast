package com.atividade.apiRest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MODELO")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "Campo nome de modelo está vazio !!")
	private String nome;

	@ManyToOne
	private Marca marca;

	public Modelo(Modelo modelo) {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo(Integer id, String nome, Marca marca) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
	}

	public Modelo() {
		super();
	}
	
	

}
