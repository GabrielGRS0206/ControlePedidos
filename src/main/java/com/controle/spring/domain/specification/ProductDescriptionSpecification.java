package com.controle.spring.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.controle.spring.domain.model.Product;

public class ProductDescriptionSpecification implements Specification<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "description";
	
	private String description;
	
	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.like(root.get(DESCRIPTION),"%"+ DESCRIPTION + "%");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
