package br.com.controle.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.repository.CashRegisterRepository;
import br.com.controle.domain.utils.Utils;

@Service
public class CashRegisterService {

	@Autowired
	public CashRegisterRepository repository;

	public CashRegister save(Object obj) {
		return repository.save((CashRegister) obj);
	}

	public CashRegister update(Object obj) {
		Optional<CashRegister> entity = findById(((CashRegister) obj).getId());

		if (entity.isPresent()) {
			entity.get().setTotalClosure(((CashRegister) obj).getTotalClosure());
			repository.save(entity.get());
		}
		return entity.get();
	}

	public Optional<CashRegister> findById(long id) {
		if (!existsById(id)) {
			throw new BusinessException(MessageException.CASH_REGISTER_NOT_FOUND.getValue(), id);
		}
		return repository.findById(id);
	}

	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	public boolean openCashRegister(Long id) {
		Optional<CashRegister> entity = findById(id);

		if (entity.isPresent()) {
			if (Utils.valueDiffZero(entity.get().getTotalClosure())) {
				return true;
			}
		}
		return false;
	}
}
