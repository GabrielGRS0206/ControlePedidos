package com.controle.spring.domain.repository.infrasctruture.repository.client;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.controle.spring.domain.model.Client;
import com.controle.spring.domain.specification.ClientNameSpecification;
import com.controle.spring.domain.utils.SpringUtils;

@Repository
public class ClientRepositoryImpl implements ClientRepositoryQueries{


	@PersistenceContext
	public EntityManager manager;

	@Override
	public Page<Client> search(ClientNameSpecification filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);

		Root<Client> root = criteria.from(Client.class);

		Predicate[] predicates = getPredicates(filter,root,builder);
		criteria.where(predicates);

		TypedQuery<Client> query = manager.createQuery(criteria);
		restricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, getTotal(filter));
	}

	public Predicate[] getPredicates(ClientNameSpecification filter,Root<Client> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();


		if (SpringUtils.isEmpty(filter.getName())) {
			predicates.add(builder.like(root.get(ClientNameSpecification.NAME), "%" + filter.getName().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	public void restricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	public Long getTotal(ClientNameSpecification specification) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Client> root = criteria.from(Client.class);

		Predicate[] predicates = getPredicates(specification,root,builder);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}