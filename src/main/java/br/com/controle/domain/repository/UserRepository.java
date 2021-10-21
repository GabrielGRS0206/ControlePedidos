package br.com.controle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.UserSystem;

@Repository
public interface UserRepository extends JpaRepository<UserSystem, Long>{

	UserSystem findByEmail(String email);

}
