package br.com.controle.api.config.security;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	@Autowired
	private Logger logger;

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

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature - {}", ex.getMessage());
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token - {}", ex.getMessage());
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token - {}", ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token - {}", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty - {}", ex.getMessage());
		}
		return false;
	}

}