package br.com.controle.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.repository.ProductRepository;
import br.com.controle.domain.service.validation.DeleteProductValidation;
import br.com.controle.domain.specification.ProductDescriptionSpecification;
import br.com.controle.domain.utils.interfaces.service.Services;

@Service
public class ProductService implements Services<Product> {

	@Autowired
	public ProductRepository repository;

	@Autowired
	public DeleteProductValidation deleteProductValidation;

	@Override
	public Product save(Object obj) {
		Objects.requireNonNull(obj, MessageException.OBJECT_NOT_NULL.getValue());
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

		if (!product.isPresent()) {
			throw new BusinessException(MessageException.PRODUCT_ID_NOT_FOUND.getValue(), id);
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
