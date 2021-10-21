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
import br.com.controle.domain.model.Client;
import br.com.controle.domain.repository.ClientRepository;
import br.com.controle.domain.service.validation.DeleteClientValidation;
import br.com.controle.domain.specification.ClientNameSpecification;
import br.com.controle.domain.utils.interfaces.service.Services;

@Service
public class ClientService implements Services<Client> {

	@Autowired
	public ClientRepository repository;

	@Autowired
	public DeleteClientValidation deleteValidation;

	@Override
	public Client save(Object obj) {
		Objects.requireNonNull(obj, MessageException.OBJECT_NOT_NULL.getValue());
		Optional<Client> object = repository.findByDocument(((Client) obj).getDocument());

		if (object.isPresent()) {
			throw new BusinessException("Cliente já cadastrado,código :" + object.get().getId());
		}

		return repository.save((Client) obj);
	}

	@Override
	public Client update(Object entity) {
		return repository.save((Client) entity);
	}

	@Override
	public void delete(long id) {
		deleteValidation.delete(id);
	}

	@Override
	public Optional<Client> findById(long id) {
		Optional<Client> entity = repository.findById(id);

		if (!entity.isPresent()) {
			throw new BusinessException(MessageException.MSG_CLIENTE_NAO_ENCONTRADO.getValue(), id);
		}

		return entity;
	}

	@Override
	public List<Client> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	public Optional<Client> findByDocument(String document) {
		Optional<Client> entity = repository.findByDocument(document);

		if (!entity.isPresent()) {
			throw new BusinessException("Cliente com CPF/CNPJ " + document + " não encontrado");
		}

		return entity;
	}

	public Page<Client> search(ClientNameSpecification filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}
}
