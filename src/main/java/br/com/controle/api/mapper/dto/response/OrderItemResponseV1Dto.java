package br.com.controle.api.mapper.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.controle.domain.model.OrderItem;

@JsonInclude(Include.NON_NULL)
public class OrderItemResponseV1Dto {

	private Long idItem;
	private Long productId;
	private String description;
	private BigDecimal quantity;
	private BigDecimal total;
	private String observation;

	public static OrderItemResponseV1Dto toDto(OrderItem item) {
		OrderItemResponseV1Dto dto = new OrderItemResponseV1Dto();
		dto.setIdItem(item.getId());
		dto.setProductId(item.getProductId());
		dto.setDescription(item.getProduct().getDescription());
		dto.setObservation(item.getObservation());
		dto.setQuantity(item.getQuantity());
		dto.setTotal(item.totalItem());
		return dto;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

}
