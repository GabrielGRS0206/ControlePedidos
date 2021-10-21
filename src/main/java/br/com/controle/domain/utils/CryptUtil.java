package br.com.controle.domain.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtil {

	private static final String SECRET = "12563985646545";

	public static boolean pswOk(String password, String passwordDb) {
		if(hash(password).equals(passwordDb)) {
			return true;
		}
		return false;
	}

	public static String hash(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(value.trim().getBytes()));
		return hash.toString(16).concat(SECRET);
	}
}
