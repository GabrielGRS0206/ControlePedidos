package br.com.controle.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "cash_register")
public class CashRegister extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 15, scale = 2)
	private BigDecimal totalOpening;

	@Column(precision = 15, scale = 2)
	private BigDecimal totalClosure;

	@Column
	private LocalDate date;

	public CashRegister(LocalDate data, BigDecimal valor, boolean open) {

		if (open) {
			this.date = data;
			this.totalOpening = valor;
			this.totalClosure = BigDecimal.ZERO;
		} else {
			this.totalOpening = BigDecimal.ZERO;
			this.totalClosure = valor;
		}
	}

	public CashRegister(Long cashRegisterId) {
		this.id = cashRegisterId;
	}

    public CashRegister() {
        super();
    }
}