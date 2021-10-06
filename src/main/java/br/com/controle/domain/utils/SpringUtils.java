package br.com.controle.domain.utils;

import java.math.BigDecimal;

import com.google.common.base.Strings;

public class SpringUtils {

	public static String replic(String string, int i) {
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < i; x++) {
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

	public static boolean isEmpty(String nome) {
		return Strings.isNullOrEmpty(nome);
	}

	public static boolean isEmpty(Integer params) {

		boolean retorno = true;
		if (params == null) {
			retorno = false;
		} else if (params != null && params > 0) {
			retorno = true;
		}
		return retorno;
	}

	public static BigDecimal removeNull(BigDecimal valor) {
		return valor != null ? valor : new BigDecimal(0);
	}

	public static boolean valueDiffZero(BigDecimal valor) {
		if (SpringUtils.removeNull(valor).compareTo(BigDecimal.ZERO) != 0) {
			return true;
		}
		return false;
	}
}
