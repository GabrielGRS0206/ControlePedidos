package br.com.controle.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(Include.NON_NULL)
@Data
public class OrderItemResponseV1Dto extends BaseResponseDto {

	private Long idItem;
	private Long productId;
	private String description;
	private BigDecimal quantity;
	private BigDecimal total;
	private String observation;
}
