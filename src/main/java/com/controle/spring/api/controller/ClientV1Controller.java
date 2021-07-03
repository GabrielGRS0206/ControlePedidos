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

import com.controle.spring.api.converter.ClientV1Converter;
import com.controle.spring.api.dto.input.ClientInputV1Dto;
import com.controle.spring.api.dto.output.ClientOutputV1Dto;
import com.controle.spring.domain.model.Client;
import com.controle.spring.domain.service.ClientService;
import com.controle.spring.domain.service.validation.DeleteClientValidation;
import com.controle.spring.domain.specification.ClientNameSpecification;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/customers")
public class ClientV1Controller extends BaseController {

	public ClientService service;

	public DeleteClientValidation deleteValidation;

	public ClientV1Converter converter;

	public ClientV1Controller(ClientService clientService,
			DeleteClientValidation deleteValidation,
			ClientV1Converter converter) {
		this.service = clientService;
		this.deleteValidation = deleteValidation;
		this.converter = converter;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente adicionado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid ClientInputV1Dto input) {

		Client client = converter.dtoToEntity(input);
		service.save(client);
		ClientOutputV1Dto dto = converter.entityToDto(client);

		return created(dto);
	}

	@GetMapping
	@ApiOperation("Listar todos clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clientes recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<ClientOutputV1Dto> findAll(){
		return converter.listToDto(service.findAll());
	}

	@GetMapping("/filter")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clientes filtrados recuperadas com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<ClientOutputV1Dto> cliente(ClientNameSpecification filter,Pageable pageable) {

		Page<Client> list = service.search(filter, pageable);
		Page<ClientOutputV1Dto> listaDto = new PageImpl<>(
				list.getContent().stream().map(x -> converter.entityToDto(x)).collect(Collectors.toList()),
				list.getPageable(), list.getTotalElements());

		return listaDto;
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
	public ResponseEntity<Object> update(@Valid @PathVariable Long id,
			@RequestBody ClientInputV1Dto input) {

		Client clientUpdate = converter.dtoToEntity(input);
		clientUpdate.setId(id);

		service.update(clientUpdate);
		ClientOutputV1Dto dto = converter.entityToDto(clientUpdate);

		return ok(dto);
	}


	@GetMapping("/{id}")
	@ApiOperation("Consultar cliente por código")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Client> client = service.findById(id);

		if (client.isPresent()) {
			ClientOutputV1Dto dto = converter.entityToDto(client.get());
			return ok(dto);
		}

		return notFound();
	}

	@GetMapping("/document/{document}")
	@ApiOperation("Consultar cliente por CPF/CNPJ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findByCpfOrCnpj(@PathVariable String document) {
		
		Optional<Client> client = service.findByDocument(document);
		ClientOutputV1Dto dto = converter.entityToDto(client.get());

		return ok(dto);
	}
}

