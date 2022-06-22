package br.com.controle.api.dto.request;

import br.com.controle.domain.model.CashRegister;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CashRegisterOpenRequestV1Dto {

	private LocalDate data;
	private BigDecimal total;
	
	public static CashRegister dtoToEntity(@Valid CashRegisterOpenRequestV1Dto caixaInput) {
		return new CashRegister(LocalDate.now(),caixaInput.getTotal(),true);
	}
}
