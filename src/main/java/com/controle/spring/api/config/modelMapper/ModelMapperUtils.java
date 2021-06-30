package com.controle.spring.api.config.modelMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtils{

	public ModelMapperConfig config;

	public ModelMapper modelMapper;

	public ModelMapperUtils(ModelMapperConfig config,ModelMapper modelMapper) {
		this.config = config;
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
	}

	public <T> Object toModel(Object object,Class<T> classe) {
		return modelMapper.map(object, classe);
	}

	public <S, T> List<T> toModelList(List<S> source, Class<T> targetClass) {
		return source
				.stream()
				.map(element -> modelMapper.map(element, targetClass))
				.collect(Collectors.toList());
	}
}
