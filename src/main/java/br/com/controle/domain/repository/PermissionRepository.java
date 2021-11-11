package br.com.controle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.security.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
}
