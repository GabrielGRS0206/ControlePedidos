package br.com.controle.domain.model.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

}
