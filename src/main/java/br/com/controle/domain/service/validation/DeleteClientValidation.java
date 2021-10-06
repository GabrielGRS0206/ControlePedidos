package br.com.controle.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.controle.domain.exception.business.EntityInUseException;
import br.com.controle.domain.exception.not_found.ClientNotFoundException;
import br.com.controle.domain.repository.ClientRepository;

@Component
public class DeleteClientValidation implements Validation {

	@Autowired
	public ClientRepository clienteRepository;

	@Override
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ClientNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Cliente de código %d não pode ser removido, pois está em uso", id));
		}
	}

}
