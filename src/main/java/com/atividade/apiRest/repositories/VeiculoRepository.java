package com.atividade.apiRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.apiRest.models.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
	
	public List<Veiculo> findAll();

}
