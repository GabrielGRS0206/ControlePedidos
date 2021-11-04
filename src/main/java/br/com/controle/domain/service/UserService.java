package br.com.controle.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.Permission;
import br.com.controle.domain.model.UserSystem;
import br.com.controle.domain.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PermissionService permissionService;

	public void update(UserSystem user) {
		repository.save(user);
	}

	public Collection<? extends GrantedAuthority> authorities(UserSystem user) {
		Collection<GrantedAuthority> auths = new ArrayList<>();

		List<Permission> lista = permissionService.findPermissionUserId(user.getId());

		for (Permission role : lista) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + role.getNome()));
		}
		return auths;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserSystem user = repository.findByEmail(username).orElse(null);
		if (user == null) {
			throw new BusinessException(MessageException.INVALID_USER.getValue());
		}
		authorities(user);
		return user;
	}
}
