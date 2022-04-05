package br.com.controle.api.model.dto.request;

import java.math.BigDecimal;

import br.com.controle.domain.model.OrderItem;

public class OrderItemRequestV1Dto {

	private Long id;
	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal additional;
	private String observation;

	public static OrderItem dtoToEntity(OrderItemRequestV1Dto item) {
		var entity = new OrderItem();
		entity.setProductId(item.getId());
		entity.getProduct().setUnitPrice(item.getPrice());
		entity.setObservation(item.getObservation());
		entity.setQuantity(item.getQuantity());
		entity.setPrice(entity.getProduct().getUnitPrice());
		return entity;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAdditional() {
		return additional;
	}

	public void setAdditional(BigDecimal additional) {
		this.additional = additional;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

}
