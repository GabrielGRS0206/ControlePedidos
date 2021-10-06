package br.com.controle.domain.repository.infrasctruture.repository.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.StatusOrder;

public class OrderRepositoryImpl implements OrderRepositoryQueries {

	@Autowired
	public JdbcTemplate template;

	@Override
	public List<Order> openOrder() {
		List<Order> list = new ArrayList<>();

		try {

			String sql = " select c.* from tb_order c "
					+ " where c.status = '"+StatusOrder.ABERTA.getDescription()+"' ";

			list = template.query(
					sql,
					(rs, rowNum) ->
					new Order(
							rs.getLong("id_order"),
							rs.getDate("data").toLocalDate(),
							rs.getString("contact"),
							rs.getString("name_client"),
							rs.getBigDecimal("total")
							)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean cancelOrder(long id) {
		try {

			String sql = " update tb_order c set c.status ='"+StatusOrder.CANCELADA+"' "
					+ " where c.id_order = "+id;

			template.execute(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void deleteItens(long id) {
		try {
			String sql = " delete from order_item where id_order = "+id;
			template.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
