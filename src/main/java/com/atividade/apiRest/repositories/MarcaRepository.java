package com.atividade.apiRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atividade.apiRest.models.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	
	public List<Marca> findAll();

	@Query("SELECT obj FROM Marca obj WHERE obj.nome =:nome")
	Marca findByNome(@Param("nome") String nome);

	

}
