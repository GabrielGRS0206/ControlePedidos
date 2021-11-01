package br.com.controle.domain.repository.infrasctruture.repository.dailyClosing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.Payment;
import br.com.controle.domain.movement.DailyClosing;

@Repository
public class DailyClosingImpl implements DailyClosingQueries {

	private final Logger LOGGER = LoggerFactory.getLogger(DailyClosingImpl.class);
	
	@Autowired
	public JdbcTemplate template;

	@Override
	public List<DailyClosing> totalCashRegister(long id) {
		List<DailyClosing> list = new ArrayList<>();

		try {

			String sql = " SELECT " + " (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = "
					+ Payment.A_VISTA.getCod() + " AND v.cash_register_id = #ID) AS " + Payment.A_VISTA.getValueBd()
					+ ", " + " (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = "
					+ Payment.CARTAO_CREDITO.getCod() + " AND v.cash_register_id = #ID) AS "
					+ Payment.CARTAO_CREDITO.getValueBd() + ", "
					+ " (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = " + Payment.CARTAO_DEBITO.getCod()
					+ " AND v.cash_register_id = #ID) AS " + Payment.CARTAO_DEBITO.getValueBd() + ", "
					+ " (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = " + Payment.BRINDE.getCod()
					+ " AND v.cash_register_id = #ID) AS " + Payment.BRINDE.getValueBd() + ", "
					+ " (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = " + Payment.PIX.getCod()
					+ " AND v.cash_register_id = #ID) AS " + Payment.PIX.getValueBd() + ", "
					+ " (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = " + Payment.OUTROS.getCod()
					+ " AND v.cash_register_id = #ID) AS " + Payment.OUTROS.getValueBd() + " " + " FROM tb_order c "
					+ " LIMIT 1 ";

			sql = sql.replaceAll("#ID", String.valueOf(id));

			List<Map<String, Object>> rows = template.queryForList(sql);

			Map<?, ?> row = rows.get(0);

			list.add(new DailyClosing(Payment.A_VISTA.getCod(), (BigDecimal) row.get(Payment.A_VISTA.getValueBd())));
			list.add(new DailyClosing(Payment.CARTAO_CREDITO.getCod(),
					((BigDecimal) row.get(Payment.CARTAO_CREDITO.getValueBd()))));
			list.add(new DailyClosing(Payment.CARTAO_DEBITO.getCod(),
					((BigDecimal) row.get(Payment.CARTAO_DEBITO.getValueBd()))));
			list.add(new DailyClosing(Payment.BRINDE.getCod(), ((BigDecimal) row.get(Payment.BRINDE.getValueBd()))));
			list.add(new DailyClosing(Payment.PIX.getCod(), (BigDecimal) row.get(Payment.PIX.getValueBd())));
			list.add(new DailyClosing(Payment.OUTROS.getCod(), ((BigDecimal) row.get(Payment.OUTROS.getValueBd()))));
		} catch (Exception e) {
			LOGGER.error("Erro ao buscar totais por cash register",e.getCause(),e.getMessage());
		}
		return list;
	}

}
