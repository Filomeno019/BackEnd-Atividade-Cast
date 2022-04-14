package com.atividade.apiRest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Table(name = "MARCA")
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "Campo nome da marca está vazio !!")
	private String nome;

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
	
	public Marca() {
	}

//	public Marca(Integer id, String nome, List<Modelo> modelo) {
//		super();
//		this.id = id;
//		this.nome = nome;
//	}

	@JsonCreator
	public Marca(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome; 
	}

	public Marca(Marca marca) {
		// TODO Auto-generated constructor stub
	}
	 
	
	
}
