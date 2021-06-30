package com.controle.spring.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.controle.spring.api.config.modelMapper.ModelMapperConfig;
import com.controle.spring.api.dto.input.OrderInputV1Dto;
import com.controle.spring.api.dto.output.OrderAbstractOutputV1Dto;
import com.controle.spring.api.dto.output.OrderOutputV1Dto;
import com.controle.spring.domain.model.Order;

@Component
public class OrderV1Converter extends ValidConstrains<Order>{

	public ModelMapperConfig config;

	public ModelMapper modelMapper;

	public OrderV1Converter(ModelMapperConfig config,ModelMapper modelMapper) {
		this.config = config;
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
	}

	public OrderOutputV1Dto entityToDto(Order order) {
		OrderOutputV1Dto dto = modelMapper.map(order, OrderOutputV1Dto.class);
		return dto;
	}

	public List<OrderOutputV1Dto> listToDto(List<Order> list) {
		return list
				.stream()
				.map(element -> modelMapper.map(element, OrderOutputV1Dto.class))
				.collect(Collectors.toList());
	}

	public Order dtoToEntity(OrderInputV1Dto input) {
		Order entity = modelMapper.map(input, Order.class);
		isValid(entity);
		return entity;
	}
	
	public List<OrderAbstractOutputV1Dto> listToDtoAbstract(List<Order> list) {
		return list
				.stream()
				.map(element -> modelMapper.map(element, OrderAbstractOutputV1Dto.class))
				.collect(Collectors.toList());
	}
}
