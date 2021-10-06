package br.com.controle.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.Client;
import br.com.controle.domain.repository.infrasctruture.repository.client.ClientRepositoryQueries;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>,ClientRepositoryQueries{

	Optional<Client> findByDocument(String document);
}
