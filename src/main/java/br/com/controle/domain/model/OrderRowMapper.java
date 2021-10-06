package br.com.controle.domain.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Order(
				rs.getLong("id_order"),
				rs.getDate("data").toLocalDate(),
				rs.getString("contact"),
				rs.getString("name_client"),
				rs.getBigDecimal("total"));
	}
}
