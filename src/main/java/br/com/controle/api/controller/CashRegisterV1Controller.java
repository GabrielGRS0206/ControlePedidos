package br.com.controle.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.api.mapper.dto.request.CashRegisterClosureRequestV1Dto;
import br.com.controle.api.mapper.dto.request.CashRegisterOpenRequestV1Dto;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.service.CashRegisterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/cash_register")
public class CashRegisterV1Controller extends BaseController {

	@Autowired
	public CashRegisterService service;

	@PostMapping("/open")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar caixa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Caixa adicionado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid CashRegisterOpenRequestV1Dto request) {

		CashRegister entity = CashRegisterOpenRequestV1Dto.dtoToEntity(request);
		service.save(entity);

		return created("Caixa: " + entity.getId());
	}

	@PostMapping("/close/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Fechamento caixa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fechamento de caixa efetuado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@RequestBody @Valid CashRegisterClosureRequestV1Dto request,
			@PathVariable Long id) {

		CashRegister entity = CashRegisterClosureRequestV1Dto.dtoToEntity(request);
		entity.setId(id);
		service.update(entity);

		return ok("Caixa " + entity.getId() + " fechado com sucesso..");
	}
}
