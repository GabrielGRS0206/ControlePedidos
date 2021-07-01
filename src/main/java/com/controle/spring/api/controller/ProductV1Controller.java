package com.controle.spring.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controle.spring.api.converter.ProductV1Converter;
import com.controle.spring.api.dto.input.ProductInputV1Dto;
import com.controle.spring.api.dto.output.ProductOutputV1Dto;
import com.controle.spring.domain.model.Product;
import com.controle.spring.domain.service.ProductService;
import com.controle.spring.domain.specification.ProductDescriptionSpecification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/products")
@Api("Mercadorias")
public class ProductV1Controller extends BaseController{

	public ProductService service;

	public ProductV1Converter converter;

	public ProductV1Controller(ProductService service,
			ProductV1Converter converter) {
		this.service = service;
		this.converter = converter;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar mercadoria")
	@ApiResponses({
		@ApiResponse(code = 201,message = "Mercadoria adicionada com sucesso"),
		@ApiResponse(code = 401, message = "Acesso não permitido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") 
	})
	public ResponseEntity<Object> save(@Valid @RequestBody ProductInputV1Dto input) {

		Product product = converter.dtoToEntity(input);
		service.save(product);
		ProductOutputV1Dto dto = converter.entityToDto(product);

		return ok(dto);
	}

	@GetMapping
	@ApiOperation("Listar mercadorias")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mercadorias recuperadas com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<ProductOutputV1Dto> findAll(){
		return converter.listToDto(service.findAll());
	}

	@GetMapping("/filter")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mercadorias filtradas recuperadas com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<ProductOutputV1Dto> searcg(ProductDescriptionSpecification filter,Pageable pageable) {

		Page<Product> list = service.search(filter, pageable);
		Page<ProductOutputV1Dto> listaDto = new PageImpl<>(
				list.getContent().stream().map(x -> converter.entityToDto(x)).collect(Collectors.toList()),
				list.getPageable(), list.getTotalElements());

		return listaDto;
	}

	@DeleteMapping("/{id}") 
	@ApiOperation("Deletar mercadoria")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Mercadoria excluida com sucesso"),
		@ApiResponse(code = 204,message = "Não foi possível excluir"),
		@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") 
	})
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	@ApiOperation("Alterar mercadoria")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Mercadoria alterada com sucesso"),
		@ApiResponse(code = 204,message = "Não foi possível alterar a mercadoria"),
		@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") 
	})
	public ResponseEntity<Object> update(@Valid @PathVariable Long id,
			@RequestBody ProductInputV1Dto input) {

		Product productUpdate = converter.dtoToEntity(input);
		productUpdate.setId(id);
		service.update(productUpdate);
		ProductOutputV1Dto dto = converter.entityToDto(productUpdate);

		return ok(dto);
	}


	@GetMapping("/{id}")
	@ApiOperation("Consultar mercadoria por código")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Mercadoria recuperado com sucesso"),
		@ApiResponse(code = 204,message = "Não foi possível encontrar a mercadoria"),
		@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") 
	})
	public ResponseEntity<Object> findById(@PathVariable Long id) {

		Optional<Product> product = service.findById(id);
		ProductOutputV1Dto productDto = converter.entityToDto(product.get());
		
		return ok(productDto);
	}
}

