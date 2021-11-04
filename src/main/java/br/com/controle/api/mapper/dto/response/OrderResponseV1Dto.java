package br.com.controle.api.mapper.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.controle.domain.model.StatusOrder;
import br.com.controle.domain.utils.Utils;

@JsonInclude(Include.NON_NULL)
public class OrderResponseV1Dto {

	private Long id;
	private List<OrderItemResponseV1Dto> items;
	private Long clientId;
	private String name;
	private String contact;
	private String street;
	private String district;
	private String city;
	private String complement;
	private String proximity;
	private String observation;
	private StatusOrder status;
	private String delivery;
	private BigDecimal valueTotal;
	private String paymentDescription;
	private Integer paymentCod;

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public Integer getPaymentCod() {
		return paymentCod;
	}

	public void setPaymentCod(Integer paymentCod) {
		this.paymentCod = paymentCod;
	}

	private String delivery() {
		if (Utils.removeNull(delivery).equals("S")) {
			return "S";
		}
		return "N";
	}

	public List<OrderItemResponseV1Dto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponseV1Dto> items) {
		this.items = items;
	}


	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getDelivery() {
		return delivery();
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValueTotal() {
		return valueTotal;
	}

	public void setValueTotal(BigDecimal valueTotal) {
		this.valueTotal = valueTotal;
	}

}
