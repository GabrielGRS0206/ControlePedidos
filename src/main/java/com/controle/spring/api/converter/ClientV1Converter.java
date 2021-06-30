package com.controle.spring.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.controle.spring.api.config.modelMapper.ModelMapperConfig;
import com.controle.spring.api.dto.input.ClientInputV1Dto;
import com.controle.spring.api.dto.output.ClientOutputV1Dto;
import com.controle.spring.domain.model.Client;

@Component
public class ClientV1Converter extends ValidConstrains<Client>{

	public ModelMapperConfig config;

	public ModelMapper modelMapper;

	public ClientV1Converter(ModelMapperConfig config,ModelMapper modelMapper) {
		this.config = config;
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
	}

	public ClientOutputV1Dto entityToDto(Client client) {
		ClientOutputV1Dto dto = modelMapper.map(client, ClientOutputV1Dto.class);
		return dto;
	}

	public List<ClientOutputV1Dto> listToDto(List<Client> list) {
		return list
				.stream()
				.map(element -> modelMapper.map(element, ClientOutputV1Dto.class))
				.collect(Collectors.toList());
	}

	public Client dtoToEntity(ClientInputV1Dto input) {
		Client entity = modelMapper.map(input, Client.class);
		isValid(entity);
		return entity;
	}
}
