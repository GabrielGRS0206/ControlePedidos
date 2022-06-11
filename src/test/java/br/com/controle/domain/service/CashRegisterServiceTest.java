/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.repository.CashRegisterRepository;

//@RunWith(SpringRunner.class)
class CashRegisterServiceTest {

	@Mock
	private CashRegisterRepository repository;

	@InjectMocks
	private CashRegisterService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.CashRegisterService#save(java.lang.Object)}.
	 */
	@Test
	final void testSave() {
		when(repository.save(any())).thenReturn(mock(CashRegister.class));
		CashRegister entity = service.save(any());
		assertNotNull(entity);
		verify(repository, times(1)).save(any());
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.CashRegisterService#update(java.lang.Object)}.
	 */
	@Test
	final void testUpdate() {

		CashRegister casshRegister = new CashRegister();
		casshRegister.setId(1l);
		when(repository.existsById(any())).thenReturn(true);
		when(repository.findById(any())).thenReturn(Optional.of(casshRegister));

		CashRegister entity = service.update(casshRegister);
		assertNotNull(entity);
		verify(repository,times(1)).findById(any());
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.CashRegisterService#existsById(long)}.
	 */
	@Test
	final void testExistsById() {
		when(repository.existsById(any())).thenReturn(true);
		
		boolean exists = service.existsById(1l);
		
		assertTrue(exists);
		verify(repository, times(1)).existsById(any());
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.CashRegisterService#openCashRegister(java.lang.Long)}.
	 */
	@Test
	final void testOpenCashRegister() {
		
		CashRegister cashRegister = new CashRegister();
		cashRegister.setTotalClosure(BigDecimal.ONE);
		
		when(repository.existsById(any())).thenReturn(true);
		when(repository.findById(1l)).thenReturn(Optional.of(cashRegister));
		
		boolean retorno = service.openCashRegister(1l);
		
		assertTrue(retorno);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.CashRegisterService#findById(java.lang.Long)}.
	 */
	@Test
	final void testFindByIdThrowsException() {
		
		when(repository.existsById(any())).thenReturn(false);
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			 service.findById(1l);
		});
		assertNotNull(exception, "exception is null");
	}
	
	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.CashRegisterService#openCashRegister(java.lang.Long)}.
	 */
	@Test
	final void testOpenCashRegisterFalse() {
		
		when(repository.existsById(any())).thenReturn(true);
		CashRegister obj = new CashRegister();
		obj.setTotalClosure(BigDecimal.ZERO);
		when(repository.findById(1l)).thenReturn(Optional.of(obj));
		
		assertEquals(false, service.openCashRegister(1l));
	}
}
