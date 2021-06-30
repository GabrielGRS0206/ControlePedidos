package com.controle.spring.domain.movement;

import java.math.BigDecimal;

public class DailyClosing {

	private Integer codPayment;
	private String payment;
	private BigDecimal total;
	
	public DailyClosing() {
		super();
	}
	
	public DailyClosing(Integer cod,String payment,BigDecimal total) {
		this.codPayment = cod;
		this.payment = payment;
		this.total = total;
	}

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
