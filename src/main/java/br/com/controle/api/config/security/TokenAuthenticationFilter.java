package br.com.controle.api.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.controle.domain.model.UserSystem;
import br.com.controle.domain.service.TokenService;
import br.com.controle.domain.service.UserService;
import io.jsonwebtoken.Jwts;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	static final String SECRET = "MySecret";

	@Autowired
	private TokenService serviceToken;

	@Autowired
	private UserService userService;

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
		String email = serviceToken.getTokenId(tokenFromHeader);

		UserSystem user = userService.findByEmail(email);

		if (user != null) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}