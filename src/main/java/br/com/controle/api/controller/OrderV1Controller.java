package br.com.controle.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.api.mapper.OrderV1Mapper;
import br.com.controle.api.mapper.dto.request.OrderRequestV1Dto;
import br.com.controle.api.mapper.dto.response.OrderResponseV1Dto;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/order")
@Api("Pedidos")
public class OrderV1Controller extends BaseController {

	@Autowired
	public OrderService service;

	@Autowired
	public OrderV1Mapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido adicionada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@Valid @RequestBody OrderRequestV1Dto request) {
		Order entity = mapper.dtoToEntity(request);
		service.save(entity);
		return created(mapper.entityToDto(entity, OrderV1Mapper.COMPLETE));
	}

	@GetMapping("/{tipo}")
	@ApiOperation("Listar pedidos em aberto ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedidos recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<OrderResponseV1Dto> findAll(@RequestParam String tipo) {
		return mapper.listToDto(service.openOrder(), tipo);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Cancelar pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido cancelado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recuro não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> cancel(@PathVariable Long id) {
		if (service.cancel(id)) {
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
	public ResponseEntity<Object> update(@Valid @PathVariable Long id, @RequestBody OrderRequestV1Dto request) {
		Order entity = mapper.dtoToEntity(request);
		entity.setIdOrder(id);
		service.update(entity);
		return ok(mapper.entityToDto(entity, OrderV1Mapper.COMPLETE));
	}

	@GetMapping("/{id}")
	@ApiOperation("Consultar pedido por código")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Order> entity = service.findById(id);
		return ok(mapper.entityToDto(entity.get(), OrderV1Mapper.COMPLETE));
	}
}
