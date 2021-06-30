package com.controle.spring.api.dto.input;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.controle.spring.domain.model.CashRegister;

public class CashRegisterClosureInputV1Dto {

	private Integer id;
	private BigDecimal total;

	public static CashRegister dtoToEntity(@Valid CashRegisterClosureInputV1Dto caixaInput) {
		return new CashRegister(null, caixaInput.getTotal(), false);
	}

	//=======GETTERS E SETTERS========
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}


}
