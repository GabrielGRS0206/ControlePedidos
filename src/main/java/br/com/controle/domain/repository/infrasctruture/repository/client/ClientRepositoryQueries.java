package br.com.controle.domain.repository.infrasctruture.repository.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.controle.domain.model.Client;
import br.com.controle.domain.specification.ClientNameSpecification;

public interface ClientRepositoryQueries {

	public Page<Client> search(ClientNameSpecification filter, Pageable pageable);
}
