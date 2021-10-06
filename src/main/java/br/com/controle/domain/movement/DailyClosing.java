package br.com.controle.domain.movement;

import java.math.BigDecimal;

import br.com.controle.domain.model.Payment;

public class DailyClosing {

	private Integer codPayment;
	private String payment;
	private BigDecimal total;
	
	public DailyClosing() {
		super();
	}
	
	public DailyClosing(Integer cod,BigDecimal total) {
		this.codPayment = cod;
		this.payment = Payment.descriptionCod(cod);
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
