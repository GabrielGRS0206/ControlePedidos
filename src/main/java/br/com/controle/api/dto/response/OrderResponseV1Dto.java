package br.com.controle.api.dto.response;

import br.com.controle.domain.model.StatusOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Data
public class OrderResponseV1Dto extends BaseResponseDto {

	private Long id;
	private List<OrderItemResponseV1Dto> items;
	private Long clientId;
	private String name;
	private String contact;
	private String street;
	private String district;
	private String city;
	private String complement;
	private String proximity;
	private String observation;
	private StatusOrder status;
	private String delivery;
	private BigDecimal valueTotal;
	private String paymentDescription;
	private Integer paymentCod;
}
