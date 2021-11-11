package br.com.controle.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.security.UserSystem;

@Repository
public interface UserRepository extends JpaRepository<UserSystem, Long>{

	Optional<UserSystem> findByEmail(String email);

}
