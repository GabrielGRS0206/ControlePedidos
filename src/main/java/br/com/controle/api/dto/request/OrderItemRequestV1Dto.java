package br.com.controle.api.dto.request;

import br.com.controle.domain.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequestV1Dto {

	private Long id;
	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal additional;
	private String observation;
}