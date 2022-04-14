package com.atividade.apiRest.repositories;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.atividade.apiRest.models.Veiculo;
import com.atividade.apiRest.services.Pagination;

@Repository
public class NewVeiculoRepository {

	private final EntityManager em;

	public NewVeiculoRepository(EntityManager em) {
		this.em = em;
	}

	public Page newFind( Integer idMarca, Integer idModelo, BigDecimal valorDe, BigDecimal valorAte, Pageable pageable) {

		String query = "select v from Veiculo v ";
		String countQ = "Select Count(id) from Veiculo v ";
		String condicao = "where"; 
		

		if (idModelo != null) {
			query += condicao + " v.modelo.id = :idModelo";
			countQ += condicao + " v.modelo.id = :idModelo";
			condicao = " and ";
		}
		if (idMarca != null) {
			query += condicao + " v.modelo.marca.id = :idMarca";
			countQ += condicao + " v.modelo.marca.id = :idMarca";
			condicao = " and ";
		}
		if (valorDe != null) {
			query += condicao + " v.valor >= :valorDe";
			countQ += condicao + " v.valor >= :valorDe";
			condicao = " and ";
		}
		if (valorAte != null) { 
			query += condicao + " v.valor <= :valorAte";
			countQ += condicao + " v.valor <= :valorAte";
			condicao = " and ";
		}

		var q = em.createQuery(query, Veiculo.class);
		var countQuery = em.createQuery(countQ);

		if (idModelo != null) {
			q.setParameter("idModelo", idModelo);
			countQuery.setParameter("idModelo", idModelo);
		}
		if (idMarca != null) {
			q.setParameter("idMarca", idMarca);
			countQuery.setParameter("idMarca", idMarca);
		}
		if (valorDe != null) {
			q.setParameter("valorDe", valorDe);
			countQuery.setParameter("valorDe", valorDe);
		}
		if (valorAte != null) {
			q.setParameter("valorAte", valorAte);
			countQuery.setParameter("valorAte", valorAte);
		}


		q.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
		q.setMaxResults(pageable.getPageSize());
		List users = q.getResultList();

		Long countResults = (Long) countQuery.getSingleResult();

		Page teste = new Pagination(users, pageable, countResults);
		return teste;

	}

}
