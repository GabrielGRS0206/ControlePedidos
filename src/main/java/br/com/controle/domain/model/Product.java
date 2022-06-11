package br.com.controle.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode
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
}
