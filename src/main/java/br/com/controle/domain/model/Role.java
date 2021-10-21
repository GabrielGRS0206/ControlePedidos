package br.com.controle.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

}
