package br.com.controle.api.config.security;

import br.com.controle.domain.model.security.UserSystem;
import br.com.controle.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	static final String SECRET = "MySecret";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Value("#{environment.D_LOCAL}")
	private String s2;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("----->>>" + s2);

		if(true){
			var userMock = new UserSystem();
			userMock.setId(1l);
			userMock.setEmail("teste@gmail.com");
			userMock.setPassword("ONE");
			authentice(request, response, filterChain, userMock);
			return;
		}

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
			authentice(request, response, filterChain, user);
		}
	}

	private void authentice(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, UserDetails user) throws IOException, ServletException {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(request, response);
	}

	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
}