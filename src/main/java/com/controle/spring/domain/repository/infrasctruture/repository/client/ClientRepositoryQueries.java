package com.controle.spring.domain.repository.infrasctruture.repository.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controle.spring.domain.model.Client;
import com.controle.spring.domain.specification.ClientNameSpecification;

public interface ClientRepositoryQueries {

	public Page<Client> search(ClientNameSpecification filter, Pageable pageable);
}
