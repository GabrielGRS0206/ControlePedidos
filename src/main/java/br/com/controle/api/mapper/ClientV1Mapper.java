package br.com.controle.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.controle.api.dto.request.ClientRequestV1Dto;
import br.com.controle.api.dto.response.ClientResponseV1Dto;
import br.com.controle.domain.model.Client;

@Component
public class ClientV1Mapper extends ValidConstrains<Client> {

	public ClientResponseV1Dto toDto(Client client) {
		var response = new ClientResponseV1Dto();
		response.setId(client.getId());
		response.setCep(client.getCep());
		response.setCity(client.getCity());
		response.setComplement(client.getComplement());
		response.setContact(client.getContact());
		response.setDistrict(client.getDistrict());
		response.setDocument(client.getDocument());
		response.setName(client.getName());
		response.setNumber(client.getNumber());
		response.setStreet(client.getStreet());
		response.setProximity(client.getProximity());
		response.setObservation(client.getObservation());

		return response;
	}

	public List<ClientResponseV1Dto> listToDto(List<Client> list) {
		return list.stream().map(element -> toDto(element))
				.collect(Collectors.toList());
	}

	public Client toEntity(ClientRequestV1Dto request) {
		var client = new Client();
		client.setCep(request.getCep());
		client.setCity(request.getCity());
		client.setComplement(request.getComplement());
		client.setContact(request.getContact());
		client.setDistrict(request.getDistrict());
		client.setDocument(request.getDocument());
		client.setName(request.getName());
		client.setNumber(request.getNumber());
		client.setObservation(request.getObservation());
		client.setStreet(request.getStreet());
		client.setProximity(request.getProximity());
		isValid(client);
		return client;
	}
}
