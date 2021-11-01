package br.com.controle.domain.repository.infrasctruture.repository.product;

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

import br.com.controle.domain.model.Product;
import br.com.controle.domain.specification.ProductDescriptionSpecification;
import br.com.controle.domain.utils.Utils;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries{

	@PersistenceContext
	public EntityManager entityManager;

	@Override
	public Page<Product> search(ProductDescriptionSpecification filter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);

		Predicate[] predicates = getPredicates(filter,root,builder);
		criteria.where(predicates);

		TypedQuery<Product> query = entityManager.createQuery(criteria);
		restricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, getTotal(filter));
	}

	public Predicate[] getPredicates(ProductDescriptionSpecification filter,Root<Product> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();

		if (Utils.isEmpty(filter.getDescription())) {
			predicates.add(builder.like(root.get(ProductDescriptionSpecification.DESCRIPTION), "%" + filter.getDescription().toLowerCase() + "%"));
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

	public Long getTotal(ProductDescriptionSpecification filter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Product> root = criteria.from(Product.class);

		Predicate[] predicates = getPredicates(filter,root,builder);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}
}
