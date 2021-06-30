package com.controle.spring.domain.repository.infrasctruture.repository.dailyClosing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.controle.spring.domain.movement.DailyClosing;
import com.controle.spring.domain.utils.connection.ConnectionJdbc;
import com.controle.spring.domain.utils.enums.Payment;

@Repository
public class DailyClosingImpl implements DailyClosingQueries{

	@Override
	public List<DailyClosing> totalCashRegister(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DailyClosing> list = new ArrayList<DailyClosing>();
		DailyClosing dailyClosing = null;

		try {
			conn = ConnectionJdbc.getConexao();

			String sql = " SELECT "
					+" (SELECT SUM(v.total) AS v FROM order v WHERE v.forma_pagamento = 1 AND v.id_caixa = #ID) AS dinheiro, "
					+" (SELECT SUM(v.total) AS v FROM order v WHERE v.forma_pagamento = 2 AND v.id_caixa = #ID) AS cartao_credito, "
					+" (SELECT SUM(v.total) AS v FROM order v WHERE v.forma_pagamento = 3 AND v.id_caixa = #ID) AS cartao_debito, "
					+" (SELECT SUM(v.total) AS v FROM order v WHERE v.forma_pagamento = 4 AND v.id_caixa = #ID) AS brinde, "
					+" (SELECT SUM(v.total) AS v FROM order v WHERE v.forma_pagamento = 5 AND v.id_caixa = #ID) AS pix, "
					+" (SELECT SUM(v.total) AS v FROM order v WHERE v.forma_pagamento = 6 AND v.id_caixa = #ID) AS outros "
					+"	FROM comanda c "
					+"	LIMIT 1 ";

			sql = sql.replaceAll("#ID", String.valueOf(id));
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if(rs.next()) {

				//==== DINHEIRO =========
				if(rs.getBigDecimal("dinheiro") != null) {
					dailyClosing = new DailyClosing(
							Payment.DINHEIRO.getCod(),
							Payment.DINHEIRO.getDescription(),
							rs.getBigDecimal("dinheiro"));
					list.add(dailyClosing);
				}

				//==== CARTA CREDITO ====
				if(rs.getBigDecimal("cartao_credito") != null) {
					dailyClosing = new DailyClosing(
							Payment.CARTAO_CREDITO.getCod(),
							Payment.CARTAO_CREDITO.getDescription(),
							rs.getBigDecimal("cartao_credito"));
					list.add(dailyClosing);
				}

				//==== CARTAO DEBITO ====
				if(rs.getBigDecimal("cartao_debito") != null) {
					dailyClosing = new DailyClosing(
							Payment.CARTAO_DEBITO.getCod(),
							Payment.CARTAO_DEBITO.getDescription(),
							rs.getBigDecimal("cartao_debito"));
					list.add(dailyClosing);
				}

				//==== BRINDE ===========
				if(rs.getBigDecimal("brinde") != null) {
					dailyClosing = new DailyClosing(
							Payment.BRINDE.getCod(),
							Payment.BRINDE.getDescription(),
							rs.getBigDecimal("brinde"));
					list.add(dailyClosing);
				}

				//==== PIX ==============
				if(rs.getBigDecimal("pix") != null) {
					dailyClosing = new DailyClosing(
							Payment.PIX.getCod(),
							Payment.PIX.getDescription(),
							rs.getBigDecimal("pix"));
					list.add(dailyClosing);
				}

				//===== OUTROS ==========
				if(rs.getBigDecimal("outros") != null) {
					dailyClosing = new DailyClosing(
							Payment.OUTROS.getCod(),
							Payment.OUTROS.getDescription(),
							rs.getBigDecimal("outros"));
					list.add(dailyClosing);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionJdbc.close(conn);
		}
		return list;
	}

}
