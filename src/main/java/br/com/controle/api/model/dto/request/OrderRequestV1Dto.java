package br.com.controle.api.model.dto.request;

import br.com.controle.domain.model.StatusOrder;
import br.com.controle.domain.utils.Utils;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestV1Dto {

	private List<OrderItemRequestV1Dto> items;
	private Long idClient; 
	private String nameClient;
	private String contact;
	private String street;
	private String district;
	private String city;
	private String complement;
	private String proximity;
	private String observation;
	private StatusOrder status;
	private Long cashRegisterId;
	private Integer payment;
	private String delivery;

	private String orderToDelivery() {
		if(Utils.removeNull(delivery).equals("S")) {
			return "S";
		} 
		return "N";
	}
}
