package com.controle.spring.api.dto.input;

import java.util.List;

import com.controle.spring.domain.model.StatusOrder;
import com.controle.spring.domain.utils.SpringUtils;

public class OrderInputV1Dto {

	private List<OrderItemInputV1Dto> items;
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
	private Long cashRegisterId;
	private Integer payment;
	private String delivery;
	
	public String resumoOrder() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(SpringUtils.replic("=", 40)+" \n");
		
		sb.append(" \n RESUMO DA COMANDA \n");
		sb.append(" ID CLIENTE : "+idClient+" \n");
		sb.append("       NOME : "+SpringUtils.removeNull(name)+" \n");
		sb.append("    CELULAR : "+SpringUtils.removeNull(contact)+" \n");
		sb.append("        RUA : "+SpringUtils.removeNull(street)+" \n");
		sb.append("     BAIRRO : "+SpringUtils.removeNull(district)+" \n");
		sb.append("COMPLEMENTO : "+SpringUtils.removeNull(complement)+" \n");
		sb.append("PROXIMIDADE : "+SpringUtils.removeNull(proximity)+" \n");
		sb.append(" OBSERVAÇÃO : "+SpringUtils.removeNull(observation)+" \n");
		sb.append("    ENTREGA : "+orderToDelivery()+" \n");
		
		sb.append(SpringUtils.replic("-", 40)+" \n");
		sb.append("LISTA DE ITEM \n ");
		for(OrderItemInputV1Dto item : items) {
			sb.append(item.resumoComandaItem()+" \n");
		}
		sb.append(SpringUtils.replic("-", 40)+" \n \n");
		
		sb.append(SpringUtils.replic("=", 40)+" \n");
		
		return sb.toString();
	}

	private String orderToDelivery() {
		if(SpringUtils.removeNull(delivery).equals("S")) {
			return "S";
		} 
		return "N";
	}

	//=======GETTERS E SETTERS========
	public List<OrderItemInputV1Dto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemInputV1Dto> items) {
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
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	
	
}
