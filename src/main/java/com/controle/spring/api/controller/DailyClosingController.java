package com.controle.spring.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controle.spring.api.dto.output.DailyClosingV1Dto;
import com.controle.spring.domain.movement.DailyClosing;
import com.controle.spring.domain.service.DailyClosingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/daily_closing")
public class DailyClosingController extends BaseController{

	public DailyClosingService service;

	public DailyClosingController(DailyClosingService service) {
		this.service = service;
	}
	
	@GetMapping("/total_cash_register/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Recupera totais por forma de pagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dados recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> totaisPorCaixa(@PathVariable Long id) {

		List<DailyClosing> list = service.list(id);
		
		List<DailyClosingV1Dto> listDto = list
				.stream()
				.map(element -> DailyClosingV1Dto.toDto(element))
				.collect(Collectors.toList());
		return ok(listDto);
	}
}
