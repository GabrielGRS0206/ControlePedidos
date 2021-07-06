package com.controle.spring.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

	private Validator validator;
	private MessageBeanValidation<Product> beanValidation;

	@BeforeEach//Prepara classe para executar antes do teste
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		beanValidation = new MessageBeanValidation<>();
	}

	@DisplayName("Verifica as fields obrigatórias do product")
	public void validationFields() {
		// Arranjos
		Product product = new Product();

		String mensagem1 = "description campo obrigatório(a)";
		String mensagem2 = "description campo vazio";
		String mensagem3 = "unit_price campo obrigatório";
		// Execução
		Set<ConstraintViolation<Product>> violacoes = validator.validate(product);

		// Resultados
		assertNotNull(violacoes);
		assertFalse(violacoes.isEmpty());
		assertEquals(3, violacoes.size());
		assertTrue(beanValidation.verifyMessage(violacoes, mensagem1));
		assertTrue(beanValidation.verifyMessage(violacoes, mensagem2));
		assertTrue(beanValidation.verifyMessage(violacoes, mensagem3));
	}

	@Test
	@DisplayName("Verifica se unit_price é diferente de zero")
	public void verifyValueDiffZero() {
		Product product = new Product();
		
		String mensagem1 = "unit_price campo obrigatório";
		
		Set<ConstraintViolation<Product>> violacoes = validator.validate(product);
		assertNotNull(violacoes);
		
		
		assertEquals(product.getUnitPrice(),null);
//		assertTrue(beanValidation.verifyMessage(violacoes, mensagem1));
		
	}


}
