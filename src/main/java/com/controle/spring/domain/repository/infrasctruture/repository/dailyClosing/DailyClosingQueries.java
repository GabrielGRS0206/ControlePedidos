package com.controle.spring.domain.repository.infrasctruture.repository.dailyClosing;

import java.util.List;

import com.controle.spring.domain.movement.DailyClosing;

public interface DailyClosingQueries {

	public List<DailyClosing> totalCashRegister(long id);
}
