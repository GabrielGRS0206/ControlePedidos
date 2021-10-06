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

import br.com.controle.domain.utils.SpringUtils;

@Entity
@Table(name = "order_item")
public class OrderItem {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public Long getProductId() {
		return productId;
	}

	public BigDecimal totalItem() {
		return price.add(SpringUtils.removeNull(additional)).multiply(quantity);
	}
}
