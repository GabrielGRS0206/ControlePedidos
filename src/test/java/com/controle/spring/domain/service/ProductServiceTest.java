package com.controle.spring.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.controle.spring.domain.model.Product;
import com.controle.spring.domain.repository.ProductRepository;
import com.controle.spring.domain.service.validation.DeleteProductValidation;

public class ProductServiceTest {

	@Mock
	private ProductRepository repository;

	@Mock
	private DeleteProductValidation delete;

	@InjectMocks
	private ProductService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.service = new ProductService(repository, delete);
	}

	@Test
	@DisplayName("Save product")
	public void saveProduct() {

		Product product = new Product();
		product.setId(1L);
		product.setDescription("Maça");
		product.setUnitPrice(new BigDecimal("6.90"));
		product.setObservation("Teste");

		service.save(product);
		Mockito.verify(repository).save(product);
		
		assertNotNull(product);
		assertEquals(product.getId(), 1L);
		assertEquals(product.getDescription(), "Maça");
		assertEquals(product.getUnitPrice(), new BigDecimal("6.90"));
	}

	@Test
	@DisplayName("Update product")
	public void updateProduct() {

		Product product = new Product();
		product.setId(1L);
		product.setDescription("Maça");
		product.setUnitPrice(new BigDecimal("6.90"));
		product.setObservation("Teste");

		service.update(product);
		Mockito.verify(repository).save(product);

		assertNotNull(product);
		assertEquals(product.getId(), 1L);
		assertEquals(product.getDescription(), "Maça");
		assertEquals(product.getUnitPrice(), new BigDecimal("6.90"));
		assertEquals(product.getObservation(), "Teste");
	}

	@Test
	@DisplayName("Delete product")
	public void deleProduct() {
		Long id = 1L;
		service.delete(id);
		Mockito.verify(delete).delete(id);
	}

	@Test
	@DisplayName("List products")
	public void listProducts() {
		List<Product> products = products();

		Mockito.when(service.findAll()).thenReturn(products);

		List<Product> productsAux = service.findAll();

		assertEquals(productsAux.size(), 4);
	}

	@Test
	@DisplayName("Find by product id")
	public void findByProductById() {

		Optional<Product> product = Optional.of(new Product());
		product.get().setId(1L);
		product.get().setDescription("Maça");

		Mockito.when(repository.findById(1l)).thenReturn(product);

		Optional<Product> entity = service.findById(1L);

		assertEquals(entity.get().getDescription(), "Maça");
		assertEquals(entity.get().getId(), 1L);
	}

	private List<Product> products() {
		List<Product> products = new ArrayList<Product>();

		products.add(new Product(1L,"Maça",new BigDecimal("6.90")));
		products.add(new Product(1L,"Banana",new BigDecimal("7.90")));
		products.add(new Product(1L,"Melão",new BigDecimal("8.90")));
		products.add(new Product(1L,"Uva",new BigDecimal("9.90")));

		return products;
	}
}
