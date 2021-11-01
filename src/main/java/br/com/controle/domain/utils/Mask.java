package br.com.controle.domain.utils;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import br.com.controle.domain.utils.enums.TypeMascara;

public class Mask {


	public static String addMask(TypeMascara mask, Object value) {
		return addMask(mask.getMask(), value);
	}

	private static String addMask(String mascara, Object value) {
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

	public static String formatMaskIntelligent(String value) {

		if(!Utils.isEmpty(value)) {

			int tamanho = value.replaceAll("[^0-9]","").trim().length();

			if(tamanho > 0) {
				if(tamanho == 11) {
					value = addMask(TypeMascara.CPF, value);
				} else if(tamanho == 14) {
					value = addMask(TypeMascara.CNPJ, value);
				} else if(tamanho == 9) {
					value = addMask(TypeMascara.CEP, value);
				}
			}

		}

		return Utils.removeNull(value);
	}
}
