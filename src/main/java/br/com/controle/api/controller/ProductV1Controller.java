package br.com.controle.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.controle.api.mapper.ProductV1Mapper;
import br.com.controle.api.mapper.dto.request.ProductRequestV1Dto;
import br.com.controle.api.mapper.dto.response.ProductResponseV1Dto;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.service.ProductService;
import br.com.controle.domain.specification.ProductDescriptionSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/products")
@Api("Produtos")
public class ProductV1Controller extends BaseController {

	@Autowired
	public ProductService service;

	@Autowired
	public ProductV1Mapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto adicionado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@Valid @RequestBody ProductRequestV1Dto request) {
		Product entity = mapper.toEntity(request);
		service.save(entity);
		return created(mapper.toDto(entity));
	}

	@GetMapping
	@ApiOperation("Listar produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produtos recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<ProductResponseV1Dto> findAll() {
		return mapper.listToDto(service.findAll());
	}

	@GetMapping("/filter")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produtos recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<ProductResponseV1Dto> searcg(ProductDescriptionSpecification filter, Pageable pageable) {
		Page<Product> list = service.search(filter, pageable);
		return new PageImpl<>(list.getContent().stream().map(x -> mapper.toDto(x)).collect(Collectors.toList()),
				list.getPageable(), list.getTotalElements());
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Deletar produto")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto excluido com sucesso"),
			@ApiResponse(code = 204, message = "Não foi possível excluir"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	@ApiOperation("Alterar produto")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto alterado com sucesso"),
			@ApiResponse(code = 204, message = "Não foi possível alterar o produto"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@Valid @PathVariable Long id, @RequestBody ProductRequestV1Dto request) {

		Product entityUpdate = mapper.toEntity(request);
		entityUpdate.setId(id);
		service.update(entityUpdate);
		return ok(mapper.toDto(entityUpdate));
	}

	@GetMapping("/{id}")
	@ApiOperation("Consultar produto por código")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto recuperado com sucesso"),
			@ApiResponse(code = 204, message = "Não foi possível encontrar o produto"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Product> entity = service.findById(id);
		return ok(mapper.toDto(entity.get()));
	}
}
