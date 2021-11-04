package br.com.controle.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.domain.model.Permission;
import br.com.controle.domain.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository repository;

	public List<Permission> findPermissionUserId(Long id) {
		List<Permission> list = new ArrayList<>();
		list = mock();
		return list;
	}

	/**
	 * @return
	 */
	private List<Permission> mock() {
		List<Permission> list = new ArrayList<>();
		Permission p = new Permission();
		p.setId(1l);
		p.setNome("ADMIN");
		list.add(p);
		return list;
	}
}
