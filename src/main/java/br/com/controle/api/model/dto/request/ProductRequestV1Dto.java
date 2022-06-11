package br.com.controle.api.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestV1Dto {

	private Long id;
	private String description;
	private String observation;
	private BigDecimal unitPrice;

}
