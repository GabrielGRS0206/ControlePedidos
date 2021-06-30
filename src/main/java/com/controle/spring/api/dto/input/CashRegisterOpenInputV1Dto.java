package com.controle.spring.api.dto.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;

import com.controle.spring.domain.model.CashRegister;

public class CashRegisterOpenInputV1Dto {

	private LocalDate data;
	private BigDecimal total;
	
	public static CashRegister dtoToEntity(@Valid CashRegisterOpenInputV1Dto caixaInput) {
		return new CashRegister(LocalDate.now(),caixaInput.getTotal(),true);
	}
	
	//=======GETTERS E SETTERS========
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
