package br.com.controle.api.mapper.dto.response;

import java.math.BigDecimal;

import br.com.controle.domain.movement.DailyClosing;

public class DailyResponseV1Dto extends BaseResponseDto {

	private Integer codPayment;
	private String payment;
	private BigDecimal total;

	public DailyResponseV1Dto(Integer cod, String payment, BigDecimal total) {
		this.codPayment = cod;
		this.payment = payment;
		this.total = total;
	}

	public static DailyResponseV1Dto toDto(DailyClosing dailyClosing) {
		return new DailyResponseV1Dto(dailyClosing.getCodPayment(), dailyClosing.getPayment(), dailyClosing.getTotal());
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
