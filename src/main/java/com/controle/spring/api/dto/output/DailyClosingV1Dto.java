package com.controle.spring.api.dto.output;

import java.math.BigDecimal;

import com.controle.spring.domain.movement.DailyClosing;

public class DailyClosingV1Dto {

	private Integer codPayment;
	private String payment;
	private BigDecimal total;

	public DailyClosingV1Dto(Integer cod,String payment,BigDecimal total) {
		this.codPayment = cod;
		this.payment = payment;
		this.total = total;
	}

	public static DailyClosingV1Dto toDto(DailyClosing dailyClosing) {
		return new DailyClosingV1Dto(dailyClosing.getCodPayment(),
				dailyClosing.getPayment(),
				dailyClosing.getTotal());
	}

	//=======GETTERS E SETTERS========
	public Integer getCodPayment() {
		return codPayment;
	}

	public void setCodPayment(Integer codPayment) {
		this.codPayment = codPayment;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}


}
