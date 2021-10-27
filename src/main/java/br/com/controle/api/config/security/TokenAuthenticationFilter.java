package br.com.controle.api.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.controle.domain.model.UserSystem;
import br.com.controle.domain.service.TokenService;
import br.com.controle.domain.service.UserService;
import io.jsonwebtoken.Jwts;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	static final String SECRET = "MySecret";

	private final UserService userService;

	private final TokenService tokenService;

	public TokenAuthenticationFilter(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	public TokenAuthenticationFilter() {
		super();
		this.tokenService = new TokenService();
		this.userService = new UserService();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenFromHeader = getTokenFromHeader(request);
		boolean tokenValid = isTokenValid(tokenFromHeader);
		if (tokenValid) {
			this.authenticate(tokenFromHeader);
		}
		filterChain.doFilter(request, response);
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void authenticate(String tokenFromHeader) {
		String email = tokenService.getTokenId(tokenFromHeader);

		UserSystem user = mock();// userService.findByEmail(email);

		if (user != null) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	/**
	 * @return
	 */
	private UserSystem mock() {
		UserSystem user = new UserSystem();
		user.setEmail("teste@gmail.com");
		user.setId(1l);
		user.setPassword("202cb962ac59075b964b07152d234b7012563985646545");
		return user;
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}