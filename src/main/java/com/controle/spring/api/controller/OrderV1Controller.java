package com.controle.spring.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.controle.spring.api.converter.OrderV1Converter;
import com.controle.spring.api.dto.input.OrderInputV1Dto;
import com.controle.spring.api.dto.output.OrderAbstractOutputV1Dto;
import com.controle.spring.api.dto.output.OrderOutputV1Dto;
import com.controle.spring.domain.model.Order;
import com.controle.spring.domain.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/order")
@Api("Pedidos")
public class OrderV1Controller extends BaseController{

	public OrderService service;

	public OrderV1Converter converter;

	public OrderV1Controller(OrderService service,
			OrderV1Converter converter) {
		this.service = service;
		this.converter = converter;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido adicionada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@Valid @RequestBody OrderInputV1Dto orderInputV1Dto) {

		Order order = converter.dtoToEntity(orderInputV1Dto);
		order.setIdOrder(null);
		service.save(order);

		OrderOutputV1Dto dto = converter.entityToDto(order);
		dto.setItems(dto.listItemToDto(order.getItems()));

		return created(dto);
	}

	@GetMapping
	@ApiOperation("Listar pedidos em aberto ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedidos recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<OrderAbstractOutputV1Dto> findAll(){
		return converter.listToDtoAbstract(service.openOrder());
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Cancelar pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido cancelado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recuro não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> cancel(@PathVariable Long id) {

		if(service.cancel(id)) {
			return ResponseEntity.ok().body("Pedido cancelada com sucesso");
		}
		return notFound();

	}

	@PutMapping("/{id}")
	@ApiOperation("Alterar pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido alterado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@Valid @PathVariable Long id,
			@RequestBody OrderInputV1Dto input) {
		
		Order order = converter.dtoToEntity(input);
		order.setIdOrder(id);
		service.update(order);
		OrderOutputV1Dto dto = converter.entityToDto(order);
		dto.setItems(dto.listItemToDto(order.getItems()));

		return ok(dto);
	}

	@GetMapping("/{id}")
	@ApiOperation("Consultar pedido por código")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findById(@PathVariable Long id) {

		Optional<Order> order = service.findById(id);
		OrderOutputV1Dto dto = converter.entityToDto(order.get());
		dto.setItems(dto.listItemToDto(order.get().getItems()));

		return ok(dto);
	}
}

