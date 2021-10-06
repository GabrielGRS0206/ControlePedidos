package br.com.controle.domain.repository.infrasctruture.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.controle.domain.model.Product;
import br.com.controle.domain.specification.ProductDescriptionSpecification;

public interface ProductRepositoryQueries {

	public Page<Product> search(ProductDescriptionSpecification filter, Pageable pageable);
}
