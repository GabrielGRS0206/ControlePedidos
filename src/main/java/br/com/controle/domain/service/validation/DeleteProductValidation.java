package br.com.controle.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.EntityInUseException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.repository.ProductRepository;

@Component
public class DeleteProductValidation implements Validation {

	private static final String PRODUCT_IN_USE = "Mercadoria de código %d não pode ser removida, pois está em uso";

	@Autowired
	public ProductRepository repository;

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException(MessageException.MSG_PRODUCT_NOT_FOUND.getValue(), id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(PRODUCT_IN_USE, id));
		}
	}

}
