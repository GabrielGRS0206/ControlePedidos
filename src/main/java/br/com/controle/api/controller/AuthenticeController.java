package br.com.controle.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.api.mapper.dto.request.UserRequestV1DTO;
import br.com.controle.api.mapper.dto.response.UserAuthenticeResponseV1DTO;
import br.com.controle.domain.model.JwtToken;
import br.com.controle.domain.service.AuthenticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Autenticação do Usuário")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
@RequestMapping("/v1/authentice")
public class AuthenticeController extends BaseController {

	@Autowired
	private AuthenticeService authentice;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Autenticação")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Autenticado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> token(@RequestBody UserRequestV1DTO reqeuest) {
		JwtToken user = authentice.authentice(reqeuest);
		return ok(new UserAuthenticeResponseV1DTO(user));
	}
}
