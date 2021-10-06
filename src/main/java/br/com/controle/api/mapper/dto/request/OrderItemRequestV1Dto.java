package br.com.controle.api.mapper.dto.request;

import java.math.BigDecimal;

import br.com.controle.domain.model.OrderItem;

public class OrderItemRequestV1Dto {

	private Long productId;
	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal additional;
	private String observation;

	public static OrderItem dtoToEntity(OrderItemRequestV1Dto item) {
		var entity = new OrderItem();
		entity.setProductId(item.getProductId());
		entity.setObservation(item.getObservation());
		entity.setQuantity(item.getQuantity());
		entity.setPrice(entity.getProduct().getUnitPrice());
		return entity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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
