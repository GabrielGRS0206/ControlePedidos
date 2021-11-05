package br.com.controle.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(max = 120)
	@Column(length = 120)
	private String description;
	
	@Size(max = 180)
	@Column(length = 180)
	private String observation;
	
	@Column(name = "unit_price",precision = 15,scale = 2)
	private BigDecimal unitPrice;
	
	public Product() {
		super();
	}
	
	public Product(Long productId) {
		super();
		this.id = productId;
	}

	public Product(String description,String observation,BigDecimal unitPrice) {
		super();
		this.observation = observation;
		this.description = description;
		this.unitPrice = unitPrice;
	}
	
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
