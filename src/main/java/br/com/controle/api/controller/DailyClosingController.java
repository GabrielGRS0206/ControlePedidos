package br.com.controle.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.api.mapper.dto.response.DailyResponseV1Dto;
import br.com.controle.domain.movement.DailyClosing;
import br.com.controle.domain.service.DailyClosingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/daily_closing")
public class DailyClosingController extends BaseController {

	@Autowired
	public DailyClosingService service;

	@GetMapping("/total_cash_register/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Recupera totais por forma de pagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dados recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> totaisPorCaixa(@PathVariable Long id) {

		List<DailyClosing> list = service.list(id);

		List<DailyResponseV1Dto> response = list.stream().map(e -> DailyResponseV1Dto.toDto(e))
				.collect(Collectors.toList());
		return ok(response);
	}
}
