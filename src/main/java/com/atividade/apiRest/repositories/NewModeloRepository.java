package com.atividade.apiRest.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import com.atividade.apiRest.models.Modelo;
import com.atividade.apiRest.services.Pagination;

@Repository
public class NewModeloRepository {

	private final EntityManager em;
	

	public NewModeloRepository(EntityManager em) {
		this.em = em;
	}

	public Page newFind(Integer id, String nome, Pageable pageable) {

		String query = "select m from Modelo m ";
		String countQ = "Select Count(id) from Modelo m ";
		String condicao = "where";

		if (id != null) {
			query += condicao + " m.marca.id = :id";
			countQ += condicao + " m.marca.id = :id";
			condicao = " and ";
		}
		if (nome != null) {
			query += condicao + " m.nome LIKE :nome";
			countQ += condicao + " m.nome LIKE :nome";
			condicao = " and ";
		}

		var q = em.createQuery(query, Modelo.class);
		var countQuery = em.createQuery(countQ);

		if (id != null) {
			q.setParameter("id", id);
			countQuery.setParameter("id", id);
		}
		if (nome != null) {
			q.setParameter("nome", "%" + nome + "%");
			countQuery.setParameter("nome", "%" + nome + "%");
		}

		q.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
		q.setMaxResults(pageable.getPageSize());
		List users = q.getResultList();

		Long countResults = (Long) countQuery.getSingleResult();

		Page teste = new Pagination(users, pageable, countResults);
		return teste;

	}
	
	public Page findPattern(Integer id, String nome, Pageable pageable) {

		String query = "select m from Modelo m ";
		String countQ = "Select Count(id) from Modelo m ";
		String condicao = "where";

		if (id != null) {
			query += condicao + " m.id = :id";
			countQ += condicao + " m.id = :id";
			condicao = " and ";
		}
		if (nome != null) {
			query += condicao + " m.nome LIKE :nome";
			countQ += condicao + " m.nome LIKE :nome";
			condicao = " and ";
		}

		var q = em.createQuery(query, Modelo.class);
		var countQuery = em.createQuery(countQ);

		if (id != null) {
			q.setParameter("id", id);
			countQuery.setParameter("id", id);
		}
		if (nome != null) {
			q.setParameter("nome", "%" + nome + "%");
			countQuery.setParameter("nome", "%" + nome + "%");
		}

		q.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
		q.setMaxResults(pageable.getPageSize());
		List users = q.getResultList();

		Long countResults = (Long) countQuery.getSingleResult();

		Page teste = new Pagination(users, pageable, countResults);
		return teste;

	}


}
