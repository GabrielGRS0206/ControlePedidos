package br.com.controle.api.model.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.controle.domain.model.StatusOrder;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class OrderAbstractResponseV1Dto extends BaseResponseDto {

	private Long idClient;
	private String name;
	private String contact;
	private String street;
	private String district;
	private String city;
	private String observation;
	private StatusOrder status;
	private String delivery;
	private BigDecimal total;
}
