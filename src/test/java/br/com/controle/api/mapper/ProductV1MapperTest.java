/**
 * 
 */
package br.com.controle.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.controle.api.dto.request.ProductRequestV1Dto;
import br.com.controle.api.dto.response.ProductResponseV1Dto;
import br.com.controle.domain.exception.business.DtoInvalidException;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.utils.Utils;

/**
 * @author Gabriel Rocha Severino
 */
class ProductV1MapperTest {

	private ProductV1Mapper mapper = new ProductV1Mapper();

	/**
	 * Test method for
	 * {@link ProductV1Mapper#toDto(br.com.controle.domain.model.Product)}.
	 */
	@Test
	final void testToDto() {

		ProductResponseV1Dto response = mapper.toDto(mock(Product.class));
		assertNotNull(response);
	}

	/**
	 * Test method for
	 * {@link ProductV1Mapper#listToDto(java.util.List)}.
	 */
	@Test
	final void testListToDto() {

		List<ProductResponseV1Dto> list = mapper.listToDto(Arrays.asList(new Product(1l)));
		assertEquals(1,list.size());
	}

	/**
	 * Test method for
	 * {@link ProductV1Mapper#toEntity(ProductRequestV1Dto)}.
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

	/**
	 * Test method for
	 * {@link ProductV1Mapper#toEntity(ProductRequestV1Dto)}.
	 */
	@Test
	final void testToEntityThrowsException() {

		DtoInvalidException exception = assertThrows(DtoInvalidException.class, () ->{
			 mapper.toEntity(new ProductRequestV1Dto());
		});
		assertNotNull(exception,"exception is null");
	}
	
}
