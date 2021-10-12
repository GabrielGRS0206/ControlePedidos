package br.com.controle.domain.repository.infrasctruture.repository.order;

import java.util.List;

import br.com.controle.domain.model.Order;

public interface OrderRepositoryQueries {

	/**
	Lista todas comandas em aberto
	*/
	List<Order> openOrders();
	
	/**
	Deleta itens por id comanda
	*/
	void deleteItens(long id);
	/**
	Cancela comanda por id
	*/
	boolean cancelOrder(long id);
}
