package com.controle.spring.domain.repository.infrasctruture.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controle.spring.domain.model.Product;
import com.controle.spring.domain.specification.ProductDescriptionSpecification;

public interface ProductRepositoryQueries {

	public Page<Product> search(ProductDescriptionSpecification filter, Pageable pageable);
}
