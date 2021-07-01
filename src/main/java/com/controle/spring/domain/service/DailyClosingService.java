package com.controle.spring.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.controle.spring.domain.exception.notFound.CashRegisterException;
import com.controle.spring.domain.movement.DailyClosing;
import com.controle.spring.domain.repository.infrasctruture.repository.dailyClosing.DailyClosingImpl;

@Service
public class DailyClosingService{

	public DailyClosingImpl repository;
	
	public CashRegisterService cashRegisterService;

	public DailyClosingService(DailyClosingImpl repository,
			CashRegisterService cashRegister) {
		this.repository = repository;
		this.cashRegisterService = cashRegister;
	}

	public List<DailyClosing> list(long id){
		
		if(!cashRegisterService.existsById(id)) {
			throw new CashRegisterException(id);
		}
		
		return repository.totalCashRegister(id);
	}
	
	public List<DailyClosing> listV2(long id){
		
		if(!cashRegisterService.existsById(id)) {
			throw new CashRegisterException(id);
		}
		
		return repository.totalCashRegister(id);
	}
}
