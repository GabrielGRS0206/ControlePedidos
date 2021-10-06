package br.com.controle.api.mapper.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;

import br.com.controle.domain.model.CashRegister;

public class CashRegisterOpenRequestV1Dto {

	private LocalDate data;
	private BigDecimal total;
	
	public static CashRegister dtoToEntity(@Valid CashRegisterOpenRequestV1Dto caixaInput) {
		return new CashRegister(LocalDate.now(),caixaInput.getTotal(),true);
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
}
