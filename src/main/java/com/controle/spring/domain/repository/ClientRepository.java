package com.controle.spring.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.spring.domain.model.Client;
import com.controle.spring.domain.repository.infrasctruture.repository.client.ClientRepositoryQueries;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>,ClientRepositoryQueries{

	Optional<Client> findByDocument(String document);
}
