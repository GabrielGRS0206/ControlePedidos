package br.com.controle.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.movement.DailyClosing;
import br.com.controle.domain.repository.infrasctruture.repository.daily_closing.DailyClosingImpl;

@Service
public class DailyClosingService {

	
	@Autowired
	private DailyClosingImpl repository;

	@Autowired
	private CashRegisterService service;

	public List<DailyClosing> list(long id) {
		if (!service.existsById(id)) {
			throw new BusinessException(MessageException.MSG_CAIXA_NAO_ENCONTRADO.getValue(), id);
		}
		return repository.totalCashRegister(id);
	}
}
