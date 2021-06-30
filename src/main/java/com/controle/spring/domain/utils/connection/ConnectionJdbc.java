package com.controle.spring.domain.utils.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionJdbc {

	public static Connection conn;

	public static Connection getConexao() {

		try {
			if(conn == null || conn.isClosed()) {
				try {
					conn = DataSourceConnection.getConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO : "+e.getMessage());
		}

		return conn;
	}

	public static void close(Connection con){
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "Não foi possível fechar a Conexão com o banco");
		}
	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "Não foi possível fechar o ResultSet com o banco");
		} finally {
			close(con, stmt);
		}
	}

	public static void close(Connection con, Statement stmt){
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "Não foi possível fechar o Stament com o banco");
		} finally {
			close(con);
		}
	}

}
