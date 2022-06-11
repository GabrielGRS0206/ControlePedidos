package br.com.controle.api.model.dto.request;

import java.math.BigDecimal;

import javax.validation.Valid;

import br.com.controle.domain.model.CashRegister;
import lombok.Data;

@Data
public class CashRegisterClosureRequestV1Dto {

	private Integer id;
	private BigDecimal total;

	public static CashRegister dtoToEntity(@Valid CashRegisterClosureRequestV1Dto caixaInput) {
		return new CashRegister(null, caixaInput.getTotal(), false);
	}
}
