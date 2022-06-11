package br.com.controle.domain.movement;

import br.com.controle.domain.model.Payment;
import lombok.Data;

import java.math.BigDecimal;

@Data
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
}
