package com.controle.spring.api.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.controle.spring.domain.model.OrderItem;
import com.controle.spring.domain.utils.SpringUtils;
import com.controle.spring.domain.utils.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrderOutputV1Dto {

	private Long id;
	private List<OrderItemOutputV1Dto> items;
	private Long idClient; //pode ser que não tenha,pode ser usado apenas o nome
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
	
	public List<OrderItemOutputV1Dto> listItemToDto(List<OrderItem> items) {
		List<OrderItemOutputV1Dto> list = new ArrayList<OrderItemOutputV1Dto>();
		for(OrderItem item : items) {
			OrderItemOutputV1Dto itemDto = OrderItemOutputV1Dto.toDto(item);
			list.add(itemDto);
		}
		return list;
	}
	
	private String delivery() {
		if(SpringUtils.removeNull(delivery).equals("S")) {
			return "S";
		} 
		return "N";
	}

	//=======GETTERS E SETTERS========
	public List<OrderItemOutputV1Dto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemOutputV1Dto> items) {
		this.items = items;
	}
	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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


}
