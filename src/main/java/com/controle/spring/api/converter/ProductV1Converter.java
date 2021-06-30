package com.controle.spring.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.controle.spring.api.config.modelMapper.ModelMapperConfig;
import com.controle.spring.api.dto.input.ProductInputV1Dto;
import com.controle.spring.api.dto.output.ProductOutputV1Dto;
import com.controle.spring.domain.model.Product;

@Component
public class ProductV1Converter extends ValidConstrains<Product>{

	public ModelMapperConfig config;

	public ModelMapper modelMapper;

	public ProductV1Converter(ModelMapperConfig config,ModelMapper modelMapper) {
		this.config = config;
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
	}

	public ProductOutputV1Dto entityToDto(Object obj) {
		ProductOutputV1Dto dto = modelMapper.map((Product) obj, ProductOutputV1Dto.class);
		return dto;
	}

	public List<ProductOutputV1Dto> listToDto(List<Product> list) {
		return list
				.stream()
				.map(element -> modelMapper.map(element, ProductOutputV1Dto.class))
				.collect(Collectors.toList());
	}

	public Product dtoToEntity(Object obj) {
		Product entity = modelMapper.map((ProductInputV1Dto) obj, Product.class);
		isValid(entity);
		return entity;
	}
	
}
