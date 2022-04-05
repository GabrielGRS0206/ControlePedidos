package br.com.controle.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.controle.api.model.dto.request.ProductRequestV1Dto;
import br.com.controle.api.model.dto.response.ProductResponseV1Dto;
import br.com.controle.domain.model.Product;

@Component
public class ProductV1Mapper extends ValidConstrains<Product> {

	public ProductResponseV1Dto toDto(Product product) {
		var response = new ProductResponseV1Dto();
		response.setDescription(product.getDescription());
		response.setId(product.getId());
		response.setObservation(product.getObservation());
		response.setUnitPrice(product.getUnitPrice());
		return response;
	}

	public List<ProductResponseV1Dto> listToDto(List<Product> list) {
		return list.stream().map(element -> toDto(element)).collect(Collectors.toList());
	}

	public Product toEntity(ProductRequestV1Dto request) {
		var product = new Product(request.getDescription(), request.getObservation(), request.getUnitPrice());
		isValid(product);
		return product;
	}

}
