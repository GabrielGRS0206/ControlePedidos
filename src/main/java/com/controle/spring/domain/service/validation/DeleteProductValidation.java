package com.controle.spring.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.controle.spring.domain.exception.business.EntityInUseException;
import com.controle.spring.domain.exception.notFound.ProductNotFoundException;
import com.controle.spring.domain.repository.ProductRepository;

@Component
public class DeleteProductValidation implements Validation {

	@Autowired
	public ProductRepository repository;
	
	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Mercadoria de código %d não pode ser removida, pois está em uso", id));
		}
	}


}


