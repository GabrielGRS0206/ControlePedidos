package com.controle.spring.domain.repository.infrasctruture.repository.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.controle.spring.domain.model.Order;
import com.controle.spring.domain.utils.connection.ConnectionJdbc;
import com.controle.spring.domain.utils.connection.DataSourceConnection;
import com.controle.spring.domain.utils.enums.StatusOrder;

public class OrderRepositoryImpl implements OrderRepositoryQueries {

	@Override
	public List<Order> openOrder() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Order order = null;
		List<Order> list = new ArrayList<Order>();
		
		try {
			conn = DataSourceConnection.getConnection();
			
			String sql = " select c.* from order_order c "
					+ " where c.status = '"+StatusOrder.ABERTA.getDescricao()+"' ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				order = new Order();
				order.setIdOrder(rs.getLong("id_order"));
				order.setData(rs.getDate("data"));
				order.setContact(rs.getString("contact"));
				order.setNameClient(rs.getString("name_client"));
				order.setTotal(rs.getBigDecimal("total"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionJdbc.close(conn);
		}
		return list;
	}

	@Override
	public boolean cancelOrder(long id) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DataSourceConnection.getConnection();
			
			String sql = " update order c set c.status ='"+StatusOrder.CANCELADA+"' "
					+ " where c.id_order = "+id;
			
			stmt = conn.createStatement();
			stmt.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println("ERRO AO CANCELAR COMANDA :"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionJdbc.close(conn);
		}
		
		return false;
	}

	@Override
	public void deleteItens(long id) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DataSourceConnection.getConnection();
			
			String sql = " delete from order_item where id_order = "+id;
			
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println("ERRO AO CANCELAR COMANDA :"+e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionJdbc.close(conn);
		}
	}

}
