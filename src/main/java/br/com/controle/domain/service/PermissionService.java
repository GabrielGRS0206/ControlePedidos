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
		List<Permission> list = new ArrayList<Permission>();

		return list;
	}
}
