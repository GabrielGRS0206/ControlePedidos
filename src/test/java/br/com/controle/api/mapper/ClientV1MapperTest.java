/**
 * 
 */
package br.com.controle.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.controle.api.model.dto.request.ClientRequestV1Dto;
import br.com.controle.api.model.dto.response.ClientResponseV1Dto;
import br.com.controle.api.model.mapper.ClientV1Mapper;
import br.com.controle.domain.model.Client;
import br.com.controle.domain.utils.Utils;

/**
 * @author Gabriel Rocha Severino
 */
class ClientV1MapperTest {

	/**
	 * @throws java.lang.Exception
	 */
	private ClientV1Mapper mapper = new ClientV1Mapper();

	/**
	 * Test method for
	 * {@link br.com.controle.api.model.mapper.ClientV1Mapper#toDto(br.com.controle.domain.model.Client)}.
	 */
	@Test
	final void testToDto() {
		ClientResponseV1Dto response = mapper.toDto(new Client(1l));
		assertNotNull(response);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.api.model.mapper.ClientV1Mapper#listToDto(java.util.List)}.
	 */
	@Test
	final void testListToDto() {
		
		List<Client> clients = new ArrayList<Client>();
		clients.add(new Client(1l));
		clients.add(new Client(1l));
		clients.add(new Client(1l));
		
		List<ClientResponseV1Dto> response = mapper.listToDto(clients);
		assertEquals(3, response.size());
	}

	/**
	 * Test method for
	 * {@link br.com.controle.api.model.mapper.ClientV1Mapper#toEntity(br.com.controle.api.model.dto.request.ClientRequestV1Dto)}.
	 */
	@Test
	final void testToEntity() {
		ClientRequestV1Dto request = new ClientRequestV1Dto();
		request.setName(Utils.TEST);
		request.setDocument(Utils.TEST);
		
		Client client = mapper.toEntity(request);
		assertNotNull(client);
	}

}
