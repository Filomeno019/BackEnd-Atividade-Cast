package com.atividade.apiRest.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.atividade.apiRest.models.Marca;
import com.atividade.apiRest.services.Pagination;

@Repository
public class NewMarcaRepository {

	private final EntityManager em;

	public NewMarcaRepository(EntityManager em) {
		this.em = em;
	}

	public Page newFind(Integer id, String nome, Pageable pageable) {

		String query = "select C from Marca as C ";
		String countQ = "Select Count(id) from Marca as c ";
		String condicao = "where";

		if (id != null) {
			query += condicao + " C.id = :id";
			countQ += condicao + " c.id = :id";
			condicao = " and ";
		}
		if (nome != null) {
			query += condicao + " C.nome LIKE :nome";
			countQ += condicao + " c.nome LIKE :nome";
			condicao = " and ";
		}

		var q = em.createQuery(query, Marca.class);
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
