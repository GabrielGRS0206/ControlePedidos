package com.controle.spring.api.dto.input;

import java.math.BigDecimal;

import com.controle.spring.domain.utils.SpringUtils;

public class OrderItemInputV1Dto {

	private Long idProduct;
	private String description;
	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal additional;
	private String observation;

	public String resumoComandaItem() {
		StringBuilder sb = new StringBuilder();

		sb.append(idProduct+""
				+ " "+description+" "
				+ ""+SpringUtils.removeNull(observation)+""
				+ " "+quantity.toString()+""
				+ " R$"+price.add(SpringUtils.removeNull(additional)).toString()+" \n");

		return sb.toString();
	}

	//=======GETTERS E SETTERS========
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
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
