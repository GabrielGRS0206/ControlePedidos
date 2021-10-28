package br.com.controle.api.config.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	public static final String KEY = "SECRETS";
	private static final long EXPIRATION_TIME = 36000000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public String generateToken(String username) {

		String jwt = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return jwt;
	}

	public Claims decodeToken(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}

	public String getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return body.getSubject();
	}
}