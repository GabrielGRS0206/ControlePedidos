package br.com.controle.api.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ProductResponseV1Dto extends BaseResponseDto {

	private Long id;
	private String description;
	private String observation;
	private BigDecimal unitPrice;
}
