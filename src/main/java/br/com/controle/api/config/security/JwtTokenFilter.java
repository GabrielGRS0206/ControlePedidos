package br.com.controle.api.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.controle.domain.service.UserService;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	static final String SECRET = "MySecret";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (isEmpty(header) || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = header.split(" ")[1].trim();
		if (!jwtTokenUtil.validate(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		// -------------------------------------
		String email = jwtTokenUtil.getTokenId(token);

		UserDetails user = userService.loadUserByUsername(email);

		if (user != null) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			filterChain.doFilter(request, response);
		}
	}

	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}