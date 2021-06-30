package com.controle.spring.domain.model;


import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
Avaliar melhorias:
Colocar usuário para controle de abertura e fechamento de caixa
*/

@Entity
@Table(name = "cash_register")
public class CashRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 15,scale = 2)
	private BigDecimal total_opening;
	
	@Column(precision = 15,scale = 2)
	private BigDecimal total_closure;
	
	@Column
	private LocalDate date;
	
	public CashRegister(LocalDate data, BigDecimal valor,boolean abertura) {
		
		if(abertura) {
			this.date = data;
			this.total_opening = valor;
			this.total_closure = BigDecimal.ZERO;
		} else {
			this.total_opening = BigDecimal.ZERO;
			this.total_closure = valor;
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

	public BigDecimal getTotal_opening() {
		return total_opening;
	}

	public void setTotal_opening(BigDecimal total_opening) {
		this.total_opening = total_opening;
	}

	public BigDecimal getTotal_closure() {
		return total_closure;
	}

	public void setTotal_closure(BigDecimal total_closure) {
		this.total_closure = total_closure;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}