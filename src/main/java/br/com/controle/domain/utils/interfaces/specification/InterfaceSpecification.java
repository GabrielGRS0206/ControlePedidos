package br.com.controle.domain.utils.interfaces.specification;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;

public interface InterfaceSpecification<T> {

	void restricoesDePaginacao(TypedQuery<?> query, Pageable pageable);
	Long getTotal(T specification);
	Predicate[] getPredicates(Object cidadeFilter, Root<Object> root, CriteriaBuilder builder);
}
