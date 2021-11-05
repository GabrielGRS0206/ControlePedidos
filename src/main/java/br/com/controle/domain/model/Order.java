package br.com.controle.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_order")
public class Order extends BaseEntity{

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

	public Order() {
		super();
	}

	public Order(Long id,LocalDate date,String contact,String name,BigDecimal total) {
		this.setIdOrder(id);
		this.setData(date);
		this.setContact(contact);
		this.setNameClient(name);
		this.setTotal(total);
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public CashRegister getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}

	public Long getCashRegisterId() {
		return cashRegisterId;
	}

	public void setCashRegisterId(Long cashRegisterId) {
		this.cashRegister = new CashRegister(cashRegisterId);
		this.cashRegisterId = cashRegisterId;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getProximity() {
		return proximity;
	}

	public void setProximity(String proximity) {
		this.proximity = proximity;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
