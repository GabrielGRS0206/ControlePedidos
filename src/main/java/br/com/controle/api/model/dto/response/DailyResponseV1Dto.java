package br.com.controle.api.model.dto.response;

import java.math.BigDecimal;

import br.com.controle.domain.movement.DailyClosing;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
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
}
