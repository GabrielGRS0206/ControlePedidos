package com.controle.spring.domain.utils;

import java.math.BigDecimal;

public class SpringUtils {

	public static String replic(String string, int i) {
		StringBuilder sb = new StringBuilder();

		for(int x = 0; x < i;x++ ) {
			sb.append(string);
		}
		return sb.toString();
	}

	public static String removeNull(String string) {
		return string != null ? string : "";
	}

	public static Long removeNull(Long codigo) {
		return codigo != null ? codigo : 0;
	}

	public static Integer removeNull(Integer codigo) {
		return codigo != null ? codigo : 0;
	}

	public static void main(String[] args) {

		System.out.println(replic("-", 40));
	}

	public static boolean isEmpty(String nome) {
		boolean retorno = true;
		if(nome == null) {
			retorno = false;
		} else if(nome != null && nome.length() > 0) {
			retorno = true;
		}
		return retorno;
	}

	
	public static boolean isEmpty(Integer params) {
		
		boolean retorno = true;
		if(params == null) {
			retorno = false;
		} else if(params != null && params > 0) {
			retorno = true;
		}
		return retorno;
	}

	public static BigDecimal removeNull(BigDecimal valor) {
		return valor != null ? valor : new BigDecimal(0);
	}

	public static boolean valueDiffZero(BigDecimal valor) {
		if(SpringUtils.removeNull(valor).compareTo(BigDecimal.ZERO) != 0) {
			return true;
		}
		return false;
	}
}
