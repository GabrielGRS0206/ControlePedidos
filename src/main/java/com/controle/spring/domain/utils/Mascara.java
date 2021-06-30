package com.controle.spring.domain.utils;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import com.controle.spring.domain.utils.enums.TypeMascara;

public class Mascara {


	public static String adicionaMascara(TypeMascara mascara, Object value) {
		return adicionaMascara(mascara.getMascara(), value);
	}

	private static String adicionaMascara(String mascara, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			value = value == null ? "" : value;
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String formataMascaraInteligente(String value) {

		if(!SpringUtils.isEmpty(value)) {

			int tamanho = value.replaceAll("[^0-9]","").trim().length();

			if(tamanho > 0) {
				if(tamanho == 11) {
					value = adicionaMascara(TypeMascara.CPF, value);
				} else if(tamanho == 14) {
					value = adicionaMascara(TypeMascara.CNPJ, value);
				} else if(tamanho == 9) {
					value = adicionaMascara(TypeMascara.CEP, value);
				}
			}

		}

		return SpringUtils.removeNull(value);
	}
}
