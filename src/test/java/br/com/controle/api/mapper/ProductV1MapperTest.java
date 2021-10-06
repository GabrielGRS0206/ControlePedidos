/**
 * 
 */
package br.com.controle.api.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.controle.api.mapper.dto.request.ProductRequestV1Dto;
import br.com.controle.api.mapper.dto.response.ProductResponseV1Dto;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.utils.Utils;

/**
 * @author Gabriel Rocha Severino
 */
class ProductV1MapperTest {

	private ProductV1Mapper mapper = new ProductV1Mapper();

	/**
	 * Test method for
	 * {@link br.com.controle.api.mapper.ProductV1Mapper#toDto(br.com.controle.domain.model.Product)}.
	 */
	@Test
	final void testToDto() {

		ProductResponseV1Dto response = mapper.toDto(mock(Product.class));
		assertNotNull(response);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.api.mapper.ProductV1Mapper#listToDto(java.util.List)}.
	 */
	@Test
	final void testListToDto() {

		List<ProductResponseV1Dto> list = mapper.listToDto(list());
		assertEquals(list.size(), 1);
	}

	private List<Product> list() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1l));
		return list;
	}

	/**
	 * Test method for
	 * {@link br.com.controle.api.mapper.ProductV1Mapper#toEntity(br.com.controle.api.mapper.dto.request.ProductRequestV1Dto)}.
	 */
	@Test
	final void testToEntity() {

		ProductRequestV1Dto request = new ProductRequestV1Dto();
		request.setDescription(Utils.TEST);
		request.setObservation(Utils.TEST);
		request.setUnitPrice(BigDecimal.ONE);

		Product product = mapper.toEntity(request);
		assertNotNull(product);
	}

}
