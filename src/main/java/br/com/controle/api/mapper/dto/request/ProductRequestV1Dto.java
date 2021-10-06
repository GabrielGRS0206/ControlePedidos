package br.com.controle.api.mapper.dto.request;

import java.math.BigDecimal;

public class ProductRequestV1Dto {

	private Long id;
	private String description;
	private String observation;
	private BigDecimal unitPrice;

	//=======GETTERS E SETTERS========
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}




}
