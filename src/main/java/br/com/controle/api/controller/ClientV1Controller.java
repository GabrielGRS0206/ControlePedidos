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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.api.model.dto.request.ClientRequestV1Dto;
import br.com.controle.api.model.dto.response.ClientResponseV1Dto;
import br.com.controle.api.model.mapper.ClientV1Mapper;
import br.com.controle.domain.model.Client;
import br.com.controle.domain.service.ClientService;
import br.com.controle.domain.service.validation.DeleteClientValidation;
import br.com.controle.domain.specification.ClientNameSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Operações relacionadas a Cientes")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping("/v1/customers")
public class ClientV1Controller extends BaseController {

	@Autowired
	public ClientService service;

	@Autowired
	public DeleteClientValidation deleteValidation;

	@Autowired
	public ClientV1Mapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente adicionado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid ClientRequestV1Dto request) {
		Client entity = mapper.toEntity(request);
		service.save(entity);
		return created(mapper.toDto(entity));
	}

	@GetMapping
	@ApiOperation("Listar todos clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clientes recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<ClientResponseV1Dto> findAll() {
		return mapper.listToDto(service.findAll());
	}

	@GetMapping("/filter")
	@ApiOperation("Busca filtrada")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clientes recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<ClientResponseV1Dto> search(ClientNameSpecification filter, Pageable pageable) {

		Page<Client> list = service.search(filter, pageable);
		return new PageImpl<>(list.getContent().stream().map(x -> mapper.toDto(x)).collect(Collectors.toList()),
				list.getPageable(), list.getTotalElements());
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Excluir cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente excluido com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	@ApiOperation("Alterar cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente alterado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@Valid @PathVariable Long id, @RequestBody ClientRequestV1Dto request) {
		Client entityUpdate = mapper.toEntity(request);
		entityUpdate.setId(id);
		service.update(entityUpdate);
		return ok(mapper.toDto(entityUpdate));
	}

	@GetMapping("/{id}")
	@ApiOperation("Consultar cliente por código")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Client> entity = service.findById(id);
		return ok(mapper.toDto(entity.get()));
	}

	@GetMapping("/document/{document}")
	@ApiOperation("Consultar cliente por CPF/CNPJ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findByDocument(@PathVariable String document) {
		Optional<Client> entity = service.findByDocument(document);
		return ok(mapper.toDto(entity.get()));
	}
}
