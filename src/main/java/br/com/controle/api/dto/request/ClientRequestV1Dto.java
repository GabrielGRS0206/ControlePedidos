package br.com.controle.api.dto.request;

import lombok.Data;

@Data
public class ClientRequestV1Dto {

	private String name;
	private String document;
	private String contact;
	private String cep;
	private String complement;
	private String proximity;
	private String observation;
	private String number;
	private String street;
	private String district;
	private String city;
}
