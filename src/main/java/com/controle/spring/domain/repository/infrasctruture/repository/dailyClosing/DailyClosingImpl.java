package com.controle.spring.domain.repository.infrasctruture.repository.dailyClosing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.controle.spring.domain.model.Payment;
import com.controle.spring.domain.movement.DailyClosing;
import com.controle.spring.domain.utils.SpringUtils;

@Repository
public class DailyClosingImpl implements DailyClosingQueries{

	@Autowired
	public JdbcTemplate template;

	@Override
	public List<DailyClosing> totalCashRegister(long id) {
		List<DailyClosing> list = new ArrayList<DailyClosing>();
		DailyClosing dailyClosing = null;

		try {

			String sql = " SELECT "
					+" (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = 1 AND v.cash_register_id = #ID) AS dinheiro, "
					+" (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = 2 AND v.cash_register_id = #ID) AS cartao_credito, "
					+" (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = 3 AND v.cash_register_id = #ID) AS cartao_debito, "
					+" (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = 4 AND v.cash_register_id = #ID) AS brinde, "
					+" (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = 5 AND v.cash_register_id = #ID) AS pix, "
					+" (SELECT SUM(v.total) AS v FROM tb_order v WHERE v.payment1 = 6 AND v.cash_register_id = #ID) AS outros "
					+" FROM tb_order c "
					+" LIMIT 1 ";

			sql = sql.replaceAll("#ID", String.valueOf(id));


			List<Map<String, Object>> rows = template.queryForList(sql);

			Map<?, ?> row = rows.get(0);
			BigDecimal dinheiro = ((BigDecimal) row.get("dinheiro"));
			BigDecimal credito = ((BigDecimal) row.get("cartao_credito"));
			BigDecimal debito = ((BigDecimal) row.get("cartao_debito"));
			BigDecimal brinde = ((BigDecimal) row.get("brinde"));
			BigDecimal pix = ((BigDecimal) row.get("pix"));
			BigDecimal outros = ((BigDecimal) row.get("outros"));

			if(SpringUtils.valueDiffZero(dinheiro)) {
				dailyClosing = new DailyClosing(
						Payment.DINHEIRO.getCod(),
						dinheiro);
				list.add(dailyClosing);
			}

			if(SpringUtils.valueDiffZero(credito)) {
				dailyClosing = new DailyClosing(
						Payment.CARTAO_CREDITO.getCod(),
						credito);
				list.add(dailyClosing);
			}

			if(SpringUtils.valueDiffZero(debito)) {
				dailyClosing = new DailyClosing(
						Payment.CARTAO_DEBITO.getCod(),
						debito);
				list.add(dailyClosing);
			}

			if(SpringUtils.valueDiffZero(brinde)) {
				dailyClosing = new DailyClosing(
						Payment.BRINDE.getCod(),
						brinde);
				list.add(dailyClosing);
			}

			if(SpringUtils.valueDiffZero(pix)) {
				dailyClosing = new DailyClosing(
						Payment.PIX.getCod(),
						pix);
				list.add(dailyClosing);
			}

			if(SpringUtils.valueDiffZero(outros)) {
				dailyClosing = new DailyClosing(
						Payment.OUTROS.getCod(),
						outros);
				list.add(dailyClosing);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return list;
	}

}
