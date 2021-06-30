package com.controle.spring.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controle.spring.domain.model.CashRegister;
import com.controle.spring.domain.repository.CashRegisterRepository;
import com.controle.spring.domain.utils.SpringUtils;
import com.controle.spring.domain.utils.interfaces.service.Services;

@Service
public class CashRegisterService implements Services<CashRegister>{

	public CashRegisterRepository repository;

	public CashRegisterService(CashRegisterRepository caixaRepository) {
		this.repository = caixaRepository;
	}

	@Override
	public CashRegister save(Object obj) {
		return repository.save((CashRegister) obj);
	}

	@Override
	public CashRegister update(Object obj) {
		Optional<CashRegister> cash_register = findById(((CashRegister) obj).getId());
		
		if(cash_register.isPresent()) {
			cash_register.get().setTotal_closure(((CashRegister) obj).getTotal_closure());
			repository.save(cash_register.get());
		}
		return cash_register.get();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<CashRegister> findById(long id) {
		return repository.findById(id);
	}

	@Override
	public List<CashRegister> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	/**
	 * Verifica se o valor de fechamento é igual a zero
	 */
	public boolean openCashRegister(Long id) {

		Optional<CashRegister> cash_register = findById(id);

		if(cash_register.isPresent()) {
			if(SpringUtils.valueDiffZero(cash_register.get().getTotal_closure())) {
				return true;
			}
		}
		return false;
	}

}
