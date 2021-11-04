package br.com.controle.domain.repository.infrasctruture.repository.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.StatusOrder;

public class OrderRepositoryImpl implements OrderRepositoryQueries {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryImpl.class);

	@Autowired
	public JdbcTemplate template;

	@Override
	public List<Order> openOrders() {
		List<Order> list = new ArrayList<>();

		try {

			String sql = " select c.* from tb_order c " + " where c.status = '" + StatusOrder.OPEN.getDescription()
					+ "' ";

			list = template.query(sql,
					(rs, rowNum) -> new Order(rs.getLong("id_order"), rs.getDate("data").toLocalDate(),
							rs.getString("contact"), rs.getString("name_client"), rs.getBigDecimal("total")));
		} catch (Exception e) {
			LOGGER.error("Erro ao buscar ordens", e.getCause(), e.getMessage());
		}
		return list;
	}

	@Override
	public boolean cancelOrder(long id) {
		try {

			String sql = " update tb_order c set c.status ='" + StatusOrder.CANCELED + "' " + " where c.id_order = "
					+ id;

			template.execute(sql);
			return true;
		} catch (Exception e) {
			LOGGER.error("Erro ao cancelar order", e.getCause(), e.getMessage());
		}

		return false;
	}

	@Override
	public boolean deleteItens(long id) {
		try {
			String sql = " delete from order_item where id_order = " + id;
			template.execute(sql);
			return true;
		} catch (Exception e) {
			LOGGER.error("Erro ao deletar order", e.getCause(), e.getMessage());
		}
		return false;
	}

}
