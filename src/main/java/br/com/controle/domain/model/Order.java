package br.com.controle.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "tb_order")
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;

	private LocalDate data;

	@ManyToOne
	@JoinColumn(name = "id_client" )
	private Client client;

	@Column(precision = 15,scale = 2)
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "id_cash_register" )
	private CashRegister cashRegister;

	@Column(name = "cash_register_id")
	private Long cashRegisterId;

	@OneToMany(mappedBy="order") 
	private List<OrderItem> items;

	@Size(max = 120)
	@Column(length = 120)
	private String nameClient;

	@Size(max = 15)
	@Column(length = 15)
	private String contact;

	@Size(max = 15)
	@Column(length = 80)
	private String street;

	@Size(max = 120)
	@Column(length = 120)
	private String district;
	
	@Size(max = 120)
	@Column(length = 120)
	private String city;

	@Size(max = 200)
	@Column(length = 200)
	private String complement;

	@Size(max = 180)
	@Column(length = 180)
	private String proximity;

	@Column(name = "observacao",length = 180)
	private String observation;

	@Enumerated(EnumType.STRING)
	private StatusOrder status;

	@Column(name = "delivery",length = 2)
	private String delivery;

	private Integer payment;

	public Order(){
		super();
	}

	public Order(Long id,LocalDate date,String contact,String name,BigDecimal total) {
		this.setIdOrder(id);
		this.setData(date);
		this.setContact(contact);
		this.setNameClient(name);
		this.setTotal(total);
	}

	public void setCashRegisterId(Long cashRegisterId) {
		this.cashRegister = new CashRegister(cashRegisterId);
		this.cashRegisterId = cashRegisterId;
	}
}
