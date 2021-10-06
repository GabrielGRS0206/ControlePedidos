package br.com.controle.domain.repository.infrasctruture.repository.daily_closing;

import java.util.List;

import br.com.controle.domain.movement.DailyClosing;

public interface DailyClosingQueries {

	public List<DailyClosing> totalCashRegister(long id);
}
