/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.repository.ProductRepository;
import br.com.controle.domain.service.validation.DeleteProductValidation;

/**
 * @author Gabriel Rocha Severino
 *
 */
class ProductServiceTest {

	@Mock
	private ProductRepository repository;
	
	@Mock
	private DeleteProductValidation deleteProductValidation;
	
	@InjectMocks
	private ProductService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#save(java.lang.Object)}.
	 */
	@Test
	final void testSave() {
		when(repository.save(Mockito.any())).thenReturn(new Product(1l));
		Product product = new Product();
		service.save(product);
		assertNotNull(product, "product is null");
		verify(repository, times(1)).save(Mockito.any());
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#update(java.lang.Object)}.
	 */
	@Test
	final void testUpdate() {
		when(repository.save(new Product(1l))).thenReturn(new Product(1l));
		Product product = new Product();
		Product obj = service.update(product);
		assertNotNull(obj, "product is null");
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#delete(long)}.
	 */
	@Test
	final void testDelete() {
		service.delete(1l);
		verify(deleteProductValidation, times(1)).delete(1l);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#findById(long)}.
	 */
	@Test
	final void testFindById() {
		when(repository.findById(1l)).thenReturn(Optional.of(new Product(1l)));
		Optional<Product> product = service.findById(1l);
		assertNotNull(product, "product is null");
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#findById(long)}.
	 */
	@Test
	final void testFindByIdThrows() {
		when(repository.findById(1l)).thenReturn(Optional.empty());
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			service.findById(1l);
		});
		assertNotNull(exception, "exception is null");
	}
	
	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#findAll()}.
	 */
	@Test
	final void testFindAll() {
		when(repository.findAll()).thenReturn(Arrays.asList(new Product(1l)));
		
		List<Product> list = service.findAll();
		assertNotNull(list, "list is null");
		assertEquals(list.size(), 1);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.ProductService#existsById(long)}.
	 */
	@Test
	final void testExistsById() {

		when(repository.existsById(1l)).thenReturn(true);
		
		boolean retorno = service.existsById(1l);
		assertEquals(retorno, true);
	}

}
