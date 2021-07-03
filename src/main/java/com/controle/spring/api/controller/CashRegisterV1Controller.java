package com.controle.spring.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controle.spring.api.dto.input.CashRegisterOpenInputV1Dto;
import com.controle.spring.api.dto.input.CashRegisterClosureInputV1Dto;
import com.controle.spring.domain.model.CashRegister;
import com.controle.spring.domain.service.CashRegisterService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/cash_register")
public class CashRegisterV1Controller extends BaseController{

	public CashRegisterService service;

	public CashRegisterV1Controller(CashRegisterService service) {
		this.service = service;
	}
	
	@PostMapping("/open")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar caixa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Caixa adicionado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid CashRegisterOpenInputV1Dto input) {
		
		CashRegister cashRegister = (CashRegister) CashRegisterOpenInputV1Dto.dtoToEntity(input);
		service.save(cashRegister);
		
		return created("Caixa: "+cashRegister.getId());
	}
	
	@PostMapping("/close/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Fechamento caixa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fechamento de caixa efetuado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@RequestBody @Valid CashRegisterClosureInputV1Dto input,
			@PathVariable Long id) {
		
		CashRegister cashRegister = (CashRegister) CashRegisterClosureInputV1Dto.dtoToEntity(input);
		cashRegister.setId(id);
		service.update(cashRegister);
		
		return ok("Caixa "+cashRegister.getId()+" fechado com sucesso..");
	}
}
