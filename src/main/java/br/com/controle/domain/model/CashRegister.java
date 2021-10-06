package br.com.controle.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cash_register")
public class CashRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 15, scale = 2)
	private BigDecimal totalOpening;

	@Column(precision = 15, scale = 2)
	private BigDecimal totalClosure;

	@Column
	private LocalDate date;

	public CashRegister(LocalDate data, BigDecimal valor, boolean abertura) {

		if (abertura) {
			this.date = data;
			this.totalOpening = valor;
			this.totalClosure = BigDecimal.ZERO;
		} else {
			this.totalOpening = BigDecimal.ZERO;
			this.totalClosure = valor;
		}
	}

	public CashRegister() {
		super();
	}

	public CashRegister(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotalOpening() {
		return totalOpening;
	}

	public void setTotalOpening(BigDecimal totalOpening) {
		this.totalOpening = totalOpening;
	}

	public BigDecimal getTotalClosure() {
		return totalClosure;
	}

	public void setTotalClosure(BigDecimal totalClosure) {
		this.totalClosure = totalClosure;
	}
	

}