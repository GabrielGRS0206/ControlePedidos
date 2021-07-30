package com.controle.spring.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.spring.domain.exception.business.BusinessException;
import com.controle.spring.domain.exception.notFound.ClientNotFoundException;
import com.controle.spring.domain.model.Client;
import com.controle.spring.domain.repository.ClientRepository;
import com.controle.spring.domain.service.validation.DeleteClientValidation;
import com.controle.spring.domain.specification.ClientNameSpecification;
import com.controle.spring.domain.utils.interfaces.service.Services;

@Service
public class ClientService implements Services<Client> {

	public ClientRepository repository;

	public DeleteClientValidation deleteValidation;

	public ClientService(ClientRepository repository,
			DeleteClientValidation deleteValidation) {
		this.repository = repository;
		this.deleteValidation = deleteValidation;
	}

	@Override
	public Client save(Object obj) {// 16 primeiro
		Objects.requireNonNull(obj, "Objeto nao pode ser null");
		Optional<Client> client = repository.findByDocument(((Client) obj).getDocument());

		if(client.isPresent()) {
			throw new BusinessException("Cliente já cadastrado,código :"+client.get().getId());
		}

		return repository.save((Client) obj);
	}

	@Override
	public Client update(Object obj) {
		return repository.save((Client) obj);
	}

	@Override
	public void delete(long id) {
		deleteValidation.delete(id);
	}

	@Override
	public Optional<Client> findById(long id) {
		Optional<Client> client = repository.findById(id);

		if(!client.isPresent()) {
			throw new ClientNotFoundException(id);
		}

		return client;
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
		Optional<Client> client = repository.findByDocument(document);

		if(!client.isPresent()) {
			throw new ClientNotFoundException("Cliente com CPF/CNPJ "+document+" não encontrado");
		}

		return client;
	}

	public Page<Client> search(ClientNameSpecification filter, Pageable pageable) {
		return repository.search(filter, pageable);
	}

}
