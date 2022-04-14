package com.atividade.apiRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atividade.apiRest.models.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

	
	public List<Modelo> findAll();

	@Query("SELECT obj FROM Modelo obj WHERE obj.nome =:nome")
	Modelo findByNome(@Param("nome") String nome);
}
