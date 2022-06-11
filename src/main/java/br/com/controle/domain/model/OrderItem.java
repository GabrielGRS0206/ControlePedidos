package br.com.controle.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.controle.domain.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "order_item")
public class OrderItem extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;

	private Long productId;

	@ManyToOne
	@JoinColumn(name = "id_order",nullable = false)
	private Order order;

	@Column(precision = 15,scale = 2)
	private BigDecimal quantity;

	@Column(precision = 15,scale = 2)
	private BigDecimal price;

	@Column(precision = 15,scale = 2)
	private BigDecimal additional;

	@Size(max = 120)
	@Column(length = 120)
	private String observation;

	public void setProductId(Long productId) {
		this.product = new Product(productId);
		this.productId = productId;
	}

	public BigDecimal totalItem() {
		return price.add(Utils.removeNull(additional)).multiply(quantity);
	}
}
