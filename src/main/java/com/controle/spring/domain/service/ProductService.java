package com.controle.spring.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.spring.domain.exception.notFound.ProductNotFoundException;
import com.controle.spring.domain.model.Product;
import com.controle.spring.domain.repository.ProductRepository;
import com.controle.spring.domain.service.validation.DeleteProductValidation;
import com.controle.spring.domain.specification.ProductDescriptionSpecification;
import com.controle.spring.domain.utils.interfaces.service.Services;

@Service
public class ProductService implements Services<Product>{

	public ProductRepository repository;

	public DeleteProductValidation deleteProductValidation;

	public ProductService(ProductRepository repository,DeleteProductValidation validation) {
		this.repository = repository;
		this.deleteProductValidation = validation;
	}

	@Override
	public Product save(Object obj) {
		Objects.requireNonNull(obj, "Objeto nao pode ser null");
		return repository.save((Product) obj);
	}

	@Override
	public Product update(Object obj) {
		return repository.save((Product) obj);
	}

	@Override
	public void delete(long id) {
		deleteProductValidation.delete(id);
	}

	@Override
	public Optional<Product> findById(long id) {
		Optional<Product> product = repository.findById(id);

		if(!product.isPresent()) {
			throw new ProductNotFoundException(id);
		}

		return product;
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	public Page<Product> search(ProductDescriptionSpecification filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}

}
