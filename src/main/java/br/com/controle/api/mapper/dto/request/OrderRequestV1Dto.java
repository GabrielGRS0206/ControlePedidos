package br.com.controle.api.mapper.dto.request;

import java.util.List;

import br.com.controle.domain.model.StatusOrder;
import br.com.controle.domain.utils.SpringUtils;

public class OrderRequestV1Dto {

	private List<OrderItemRequestV1Dto> items;
	private Long idClient; 
	private String nameClient;
	private String contact;
	private String street;
	private String district;
	private String city;
	private String complement;
	private String proximity;
	private String observation;
	private StatusOrder status;
	private Long cashRegisterId;
	private Integer payment;
	private String delivery;

	private String orderToDelivery() {
		if(SpringUtils.removeNull(delivery).equals("S")) {
			return "S";
		} 
		return "N";
	}

	public List<OrderItemRequestV1Dto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequestV1Dto> items) {
		this.items = items;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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

	public Long getCashRegisterId() {
		return cashRegisterId;
	}

	public void setCashRegisterId(Long cashRegisterId) {
		this.cashRegisterId = cashRegisterId;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public String getDelivery() {
		return orderToDelivery();
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}



}
